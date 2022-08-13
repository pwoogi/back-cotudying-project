package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.LoginRequestDto;
import com.week06.cotudying_project.dto.RegisterDto;
import com.week06.cotudying_project.exception.LoginFailureException;
import com.week06.cotudying_project.exception.MemberUsernameAlreadyExistsException;
import com.week06.cotudying_project.jwt.RefreshToken;
import com.week06.cotudying_project.model.Authority;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member register(RegisterDto registerDto) {
        Member member = new Member();

        member.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        member.setUsername(registerDto.getUsername());
        member.setRoles("ROLE_USER");
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findUser(int id) {
        return memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User ID를 찾을 수 없습니다."));
    }
}