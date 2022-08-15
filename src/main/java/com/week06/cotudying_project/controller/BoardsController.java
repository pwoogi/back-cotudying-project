package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.boardsDto.BoardsDetailRequestDto;
import com.week06.cotudying_project.response.Response;
import com.week06.cotudying_project.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardsController {

    private final BoardsService boardsService;

    // 모집하기
    @PostMapping("/board")
    public Response createBoard(@RequestBody BoardsDetailRequestDto boardsDetailRequestDto) {
        return boardsService.createBoard(boardsDetailRequestDto);
    }

    // 전체 게시물 조회
    @GetMapping("/board")
    public Response getBoards() {
        return boardsService.getBoards();
    }

    // 게시물 상세 조회
    @GetMapping("/board/{id}")
    public Response getBoard(@PathVariable Long id) {
        return Response.success(boardsService.getBoard(id));
    }

    // 게시물 삭제
    @DeleteMapping("/board/{id}")
    public Response deletePost(@PathVariable Long id) {
        return boardsService.deleteBoards(id);
    }

//    // 참여하기 -> 참여중
//    @PutMapping("/api/board/{id}")

    // 게시물 수정
//    @PutMapping("/board/{id}")
//    public Long updateBoards(@PathVariable Long id, @RequestBody DetailRequestDto detailRequestDto) {
//        return boardsService.updateBoards(id, detailRequestDto);
//    }
}
