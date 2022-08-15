package com.week06.cotudying_project.service;

import com.week06.cotudying_project.dto.boardsDto.BoardsDetailRequestDto;
import com.week06.cotudying_project.dto.boardsDto.BoardsDetailResponseDto;
import com.week06.cotudying_project.dto.boardsDto.BoardsShowResponseDto;
import com.week06.cotudying_project.model.Boards;
import com.week06.cotudying_project.repository.BoardsRepository;
import com.week06.cotudying_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardsService {

    private final BoardsRepository boardsRepository;

    // 모집하기
    @Transactional
    public Response createBoard(BoardsDetailRequestDto boardsDetailRequestDto) {
        Boards boards = new Boards(boardsDetailRequestDto);
        boardsRepository.save(boards);

        return Response.success(200, "게시물을 올렸습니다.");
    }

    // 전체 게시물 조회
    @Transactional
    public Response getBoards() {
        List<Boards> boardList = boardsRepository.findAllByOrderByModifiedAtDesc();
        List<BoardsShowResponseDto> boardsShowResponseDtoList = new ArrayList<>();

        for(Boards boards : boardList) {
            boardsShowResponseDtoList.add(new BoardsShowResponseDto(boards));
        }
        return Response.success(boardsShowResponseDtoList);
    }

    // 게시물 상세 조회
    @Transactional
    public BoardsDetailResponseDto getBoard(Long id) {
        return BoardsDetailResponseDto.toEntity(boardsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물이 없습니다.")));
    }

    // 게시물 삭제
    @Transactional
    public Response deleteBoards(Long id) {
        Optional<Boards> optionalBoards = boardsRepository.findById(id);

        if (optionalBoards.isEmpty()) {
            return Response.failure(401, "삭제 실패");
        }
        Boards boards = optionalBoards.get();
        boardsRepository.delete(boards);
        return Response.success(200, "삭제 성공");
    }

//    // 참여하기 -> 참여중
//    @PutMapping("/api/board/{id}")

    // 게시물 수정
//    @Transactional
//    public Long updateBoards(Long id, DetailRequestDto detailRequestDto) {
//        Boards boards = boardsRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//        );
//        boards.update(detailRequestDto);
//        return boards.getId();
//    }

//    @Transactional
//    public Boards isPresentPost(Long id) {
//        Optional<Boards> optionalBoards = boardsRepository.findById(id);
//        return optionalBoards.orElse(null);
//    }
}
