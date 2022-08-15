package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.exception.MemberNotEqualsException;
import com.week06.cotudying_project.exception.MemberNotFoundException;
import com.week06.cotudying_project.model.Authority;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDto> findAllUsers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : members) {
            memberDtos.add(MemberDto.toDto(member));
        }
        return memberDtos;
    }

    @Transactional(readOnly = true)
    public MemberDto findUser(Long id) {
        return MemberDto.toDto(memberRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }


    @Transactional
    public MemberDto editUserInfo(Long id, MemberDto updateInfo) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        // 권한 처리
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals(member.getUsername())) {
            throw new MemberNotEqualsException();
        }else{
            member.setEmail(updateInfo.getEmail());
            return MemberDto.toDto(member);
        }
    }


    @Transactional
    public void deleteUserInfo(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String auth = String.valueOf(authentication.getAuthorities());
        String authByAdmin = "[" + Authority.ROLE_ADMIN + "]";

        if (authentication.getName().equals(member.getUsername()) || auth.equals(authByAdmin)) {
            memberRepository.deleteById(id);
        } else {
            throw new MemberNotEqualsException();
        }
    }
}