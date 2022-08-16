package com.week06.cotudying_project.validation;

import com.week06.cotudying_project.dto.boardsDto.BoardUpdateDto;
import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateValidation {

    public static Board validationBoardUpdate(Long id, BoardUpdateDto requestDto,
                                              BoardRepository boardRepository) {
        // 매개변수 id 유효성검사 및 study 객체 조회

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("BoardId를 찾을 수 없습니다.")
        );
        String category = requestDto.getCategory();
        String name = requestDto.getName();
        String content = requestDto.getContent();
        Long num = requestDto.getNum();
        String registerStatus = requestDto.getRegisterStatus();
        // findById한 study의 currntMemberNum을 변수로 선언
        Long participant = board.getParticipant();

        // category, name, content 확인
        // 공백, null 입력 제한
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리를 공백으로 수정할 수 없습니다.");
        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 공백으로 수정할 수 없습니다.");
        } else if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 공백으로 수정할 수 없습니다.");
        }

        // memberNum 확인
        if (num <= 1 || num > 10) {
            // 최소 2명이상부터 입력되도록 설정
            throw new IllegalArgumentException("스터디 멤버는 2 ~ 10명까지 설정가능합니다.");
        }else if(num < participant){
            throw new IllegalArgumentException("스터디 멤버는 현재 멤버 수보다 적게 수정 할 수 없습니다.");
        }else if(Objects.equals(participant, num)){
            // 수정한 memberNum과 study의 currntMemberNum이 같을때, recruitState 모집완료로 변경
            requestDto.setRegisterStatus("모집완료");
            System.out.println("모집완료 자동 변경완료");
        }

        // recruitState 확인
        if(!Objects.equals(registerStatus, board.getRegisterStatus())){
            // 수정한 recruitState와 study의 recruitState이 같지않을때만 진입
            if(!(Objects.equals(registerStatus, "모집중") || Objects.equals(registerStatus, "모집완료"))){
                throw new IllegalArgumentException("모집상태는 모집중, 모집완료만 입력 가능합니다.");
            }else if(Objects.equals(registerStatus, "모집중")){
                if(Objects.equals(participant, num)){
                    throw new IllegalArgumentException("모집인원이 가득차서 모집중으로 변경할 수 없습니다.");
                }
            }
        }
        return board;
    }
}