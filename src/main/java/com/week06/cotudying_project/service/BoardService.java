package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateRequest;
import com.week06.cotudying_project.dto.boardsDto.BoardRegisterDto;
import com.week06.cotudying_project.dto.boardsDto.BoardUpdateDto;
import com.week06.cotudying_project.exception.MemberNotFoundException;
import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.model.BoardInfo;
import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.BoardInfoRepository;
import com.week06.cotudying_project.repository.BoardRepository;
import com.week06.cotudying_project.repository.MemberRepository;
import com.week06.cotudying_project.validation.RegisterValidation;
import com.week06.cotudying_project.validation.UpdateValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardInfoRepository boardInfoRepository;


    //Study 등록
    @Transactional
    public Board createStudy(BoardCreateRequest req) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);


        Board board = new Board(req, username);
        // isEmpty() -> Collection 타입일때만
        BoardInfo boardInfo = new BoardInfo(member, boardRepository.save(board));

        boardInfoRepository.save(boardInfo);
        return board;
    }



    // Study 수정
    @Transactional
    public Board updateStudy(Long id, BoardUpdateDto requestDto) {
        // 매개변수 id, requestDto 유효성검사
        Board board = UpdateValidation.validationBoardUpdate(id, requestDto, boardRepository);
        board.updateBoard(requestDto);
        return board;
    }


    // Study 삭제
    @Transactional
    public void deleteStudy(Long id) {
        // delete할 Study find
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("deleteStudy 내부 StudyIdfind 오류 입니다.")
        );
//          메서드로 String username 빼서 구현
//        if(boardRepository.findByUsername(username).isPresent()){
//            // 게시판 if로직내에서 수정하거나 삭제 >
//            return board;
//        }
        // StudyinfoDB에서 study 필드 가진 studyInfo find
        List<BoardInfo> boardInfoList =  boardInfoRepository.findAllByBoard(board);
        for (BoardInfo boardInfo : boardInfoList) {
            if (boardInfo.getBoard() == board) {
                // cotudyList 인스턴스 study와 find한 study가 같으면 해당 studyInfo delete
                boardInfoRepository.delete(boardInfo);
            }
        }
        boardRepository.deleteById(id);
    }

    // Study 나가기
    @Transactional
    public void outStudy(Long cotudyid) {
        Board board;

        // cotudyid 유효성검사
        Optional<Board> boardOptional = boardRepository.findById(cotudyid);

        if (!boardOptional.isPresent()) {
            throw new IllegalArgumentException("secessionStudy 내부 findByIdStudy 오류.");
        } else {
            board = boardOptional.get();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        // studyInfo 유효성검사
        Optional<BoardInfo> boardInfo = boardInfoRepository.findByMemberAndBoard(member, board);
        if (!boardInfo.isPresent()) {
            throw new IllegalArgumentException("secessionStudy 내부 findByUserAndStudy 오류.");
        }

        // 스터디 탈퇴 제한 및 recruitState 자동변경
        if (board.getParticipant() == 1) {
            throw new IllegalArgumentException("스터디 Member 최후의 1인은 나갈 수 없습니다. 스터디를 삭제해주세요");
        } else if (Objects.equals(board.getRegisterStatus(), "모집완료")) {
            board.setRegisterStatus("모집중");
        }

        // 스터디 탈퇴처리
        board.setParticipant(board.getParticipant() - 1);
        boardInfoRepository.delete(boardInfo.get());

    }
}