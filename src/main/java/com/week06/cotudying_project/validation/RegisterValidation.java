package com.week06.cotudying_project.validation;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateRequest;
import com.week06.cotudying_project.exception.MemberNotFoundException;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RegisterValidation {

    public static Member validationBoardRegister(BoardCreateRequest requestDto,
                                                 MemberRepository memberRepository) {
        //UserDetails UserId 유효성검사
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);


        String category = requestDto.getCategory();
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        Long num = requestDto.getNum();
        // currentMemberNum은 스터디 생성시 1로 고정(스터디 개설자만 포함), 생성자 내용에 포함
        // recruitState는 스터디 생성시 모집중으로 고정, 생성자 내용에 포함
        // memberNum 최소가 2 이상이므로 문제없을것으로 예상

        // category, name, content 확인
        // 공백, null 입력 제한
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리를 선택해 주세요.");
        } else if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해 주세요.");
        } else if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해 주세요.");
        }

        // memberNum 확인
        if (num <= 1 || num > 10) {
            // 최소 2명이상부터 15명까지만 입력되도록 설정
            throw new IllegalArgumentException("스터디 멤버는 2 ~ 10명까지 설정가능합니다.");
        }
        return member;
    }
}
