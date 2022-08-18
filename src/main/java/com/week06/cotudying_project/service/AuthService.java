package com.week06.cotudying_project.service;

import com.week06.cotudying_project.config.jwt.TokenProvider;
import com.week06.cotudying_project.dto.*;
import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.exception.LoginFailureException;
import com.week06.cotudying_project.exception.MemberNotFoundException;
import com.week06.cotudying_project.exception.MemberUsernameAlreadyExistsException;
import com.week06.cotudying_project.model.Authority;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.model.RefreshToken;
import com.week06.cotudying_project.repository.MemberRepository;
import com.week06.cotudying_project.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void signUp(SignUpRequestDto req) {
        validateSignUpInfo(req);

        // Builder로 리팩토링 해야함
        Member member = new Member();
        member.setUsername(req.getUsername());
        member.setPassword(passwordEncoder.encode(req.getPassword()));
        member.setNickname(req.getNickname());
        member.setAuthority(Authority.ROLE_USER);
        memberRepository.save(member);
    }


    @Transactional
    public TokenResponseDto logIn(LoginRequestDto req, HttpServletResponse response) {
        Member member = memberRepository.findByUsername(req.getUsername()).orElseThrow(() -> {
            return new LoginFailureException();
        });

        validatePassword(req, member);

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = req.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);


        response.setHeader("Authorization","Bearer : " +  tokenDto.getAccessToken());
        response.setHeader("ACCESS_TOKEN_EXPIRE_TIME", String.valueOf(tokenDto.getAccessTokenExpiresIn()));

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .id(member.getId())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .build();

        // 5. 토큰 발급
        return tokenResponseDto;
    }


    @Transactional
    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto, HttpServletResponse response) {
//         1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);



        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        response.setHeader("Authorization","Bearer : "  + tokenDto.getRefreshToken());
        response.setHeader("REFRESH_TOKEN_EXPIRE_TIME", String.valueOf(tokenDto.getAccessTokenExpiresIn()));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .username(username)
                .build();


        return tokenResponseDto;
    }


    private void validateSignUpInfo(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.existsByUsername(signUpRequestDto.getUsername()))
            throw new MemberUsernameAlreadyExistsException(signUpRequestDto.getUsername());

    }

    private void validatePassword(LoginRequestDto loginRequestDto, Member member) {
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new LoginFailureException();
        }
    }
    }


