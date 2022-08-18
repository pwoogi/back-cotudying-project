package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateResponse;
import com.week06.cotudying_project.exception.MemberNotFoundException;
import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.model.BoardInfo;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.BoardInfoRepository;
import com.week06.cotudying_project.repository.MainRepository;
import com.week06.cotudying_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MainService {
    private final MainRepository mainRepository;
    private final BoardInfoRepository boardInfoRepository;
    private final MemberRepository memberRepository;

    public void registerBoard(Long id) {
        Board board;

        Optional<Board> tempBoard = mainRepository.findById(id);
        if(tempBoard.isPresent()) {
            board = tempBoard.get();
        } else {
            throw new IllegalArgumentException("해당 스터디가 없습니다.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);



        List<BoardInfo> boardInfoList = boardInfoRepository.findAllByMember(member);
        for (int i = 0; i < boardInfoList.size(); i++) {
            if(boardInfoList.get(i).getBoard().equals(board)) {
                throw new IllegalArgumentException("이미 등록된 유저입니다.");
            }
        }

        BoardInfo boardInfo = new BoardInfo();

        Long num = board.getNum();
        Long participant = board.getParticipant();

        if (participant >= num) {
            throw new IllegalArgumentException("모집인원이 이미 충족되었습니다.");
        } else {
            board.setParticipant(participant + 1);
            if(board.getParticipant().equals(num)) {
                board.setRegisterStatus("모집완료");
            }

            boardInfo.setMember(member);
            boardInfo.setBoard(mainRepository.save(board));

            boardInfoRepository.save(boardInfo);
        }

    }
    public BoardCreateResponse getBoard(Long id) {
        Board board;
        Optional<Board> boardOptional = mainRepository.findById(id);
        List<Long> memberId = new ArrayList<>();
        BoardCreateResponse boardCreateResponse = new BoardCreateResponse();

        if(boardOptional.isPresent()) {
            board = boardOptional.get();
        }
        else {
            throw new IllegalArgumentException("게시글 아이디가 잘못되었습니다.");
        }

        List<BoardInfo> boardInfoList = boardInfoRepository.findAllByBoard(board);

        for (int i = 0; i < boardInfoList.size(); i++) {
            memberId.add(boardInfoList.get(i).getMember().getId());
        }

        boardCreateResponse.setId(board.getId());
        boardCreateResponse.setRegisterUserId(board.getRegisterUserId());
        boardCreateResponse.setCategory(board.getCategory());
        boardCreateResponse.setTitle(board.getTitle());
        boardCreateResponse.setContent(board.getContent());
        boardCreateResponse.setNum(board.getNum());
        boardCreateResponse.setParticipant(board.getParticipant());
        boardCreateResponse.setRegisterStatus(board.getRegisterStatus());
        boardCreateResponse.setStartDate(board.getStartDate());
        boardCreateResponse.setEndDate(board.getEndDate());
        boardCreateResponse.setMemberId(memberId);

        return boardCreateResponse;
    }
}
