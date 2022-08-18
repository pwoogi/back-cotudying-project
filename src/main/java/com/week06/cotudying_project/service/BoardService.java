package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateRequest;
import com.week06.cotudying_project.dto.boardsDto.BoardRegisterDto;
import com.week06.cotudying_project.dto.boardsDto.BoardUpdateDto;
import com.week06.cotudying_project.exception.BoardNotFoundException;
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


        Board board = new Board(req, member.getId(), username);
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
        // delete할 Board 찾고 아니면 예외처리
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        // Boardinfo db에서 Board 필드 가진 데이터를 찾음
        List<BoardInfo> boardInfoList =  boardInfoRepository.findAllByBoard(board);
        for (BoardInfo boardInfo : boardInfoList) {
            if (boardInfo.getBoard() == board) {
                // BoardInfo와 Board db의 데이터가 같으면 삭제
                boardInfoRepository.delete(boardInfo);
            }
        }
        boardRepository.deleteById(id);
    }

    // Study 나가기
    @Transactional
    public void outStudy(Long cotudyid) {
        Board board;

        // board 유효성검사
        Optional<Board> boardOptional = boardRepository.findById(cotudyid);

        if (!boardOptional.isPresent()) {
            throw new IllegalArgumentException("스터디가 존재하지 않습니다");
        } else {
            board = boardOptional.get();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        // BoardInfo 유효성검사
        Optional<BoardInfo> boardInfo = boardInfoRepository.findByMemberAndBoard(member, board);
        if (!boardInfo.isPresent()) {
            throw new IllegalArgumentException("정보가 일치하는 스터디가 존재하지 않습니다");
        }

        // 스터디 탈퇴 제한 및 recruitStatus 자동변경
        if (board.getParticipant() == 1) {
            throw new IllegalArgumentException("마지막 참여자는 나갈 수 없습니다, 스터디를 삭제해주세요");
        } else if (Objects.equals(board.getRegisterStatus(), "모집완료")) {
            board.setRegisterStatus("모집중");
        }

        // 스터디 탈퇴처리
        board.setParticipant(board.getParticipant() - 1);
        boardInfoRepository.delete(boardInfo.get());

    }
}