package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.SignupResultDto;
import com.week06.cotudying_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignupCheckService {
    private final MemberRepository memberRepository;


    @Transactional
    public SignupResultDto checkMember(String username) {
        return new SignupResultDto(memberRepository.existsByUsername(username));
    }
}

