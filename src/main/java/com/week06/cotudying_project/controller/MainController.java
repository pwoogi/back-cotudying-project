package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateResponse;
import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.repository.MainRepository;
import com.week06.cotudying_project.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {

    private final MainService mainService;
    private final MainRepository mainRepository;

    // 전체 게시물 조회
    @GetMapping("/board")
    public List<Board> getBoards() {
        return mainRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시물 상세 조회
    @GetMapping("/board/{id}")
    public BoardCreateResponse getBoard(@PathVariable Long id) {
        return mainService.getBoard(id);
    }

    // 게시물 수정
    @PutMapping("/board/{id}")
    public void registerBoard(@PathVariable Long id) {
        mainService.registerBoard(id);
    }

    // 카테고리별 게시물
    @GetMapping("/boards/{category}")
    public List<Board> divideCategory(@PathVariable String category) throws UnsupportedEncodingException {
        return mainRepository.findAllByCategoryOrderByCreatedAtDesc(category);
    }

}
