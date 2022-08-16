package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.boardsDto.BoardCreateRequest;
import com.week06.cotudying_project.dto.boardsDto.BoardUpdateDto;
import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.model.audit.AuditingFields;
import com.week06.cotudying_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController extends AuditingFields {

    private final BoardService boardService;

    // Study 생성
    @PostMapping("/board/write")
    public Board createStudy(@RequestBody BoardCreateRequest requestDto){
        return boardService.createStudy(requestDto);
    }

    // Study 업데이트
    @PutMapping("/board/{id}/update")
    public Board updateStudy(@PathVariable Long id, @RequestBody BoardUpdateDto requestDto) {
        return boardService.updateStudy(id, requestDto);
    }

    // Study 삭제
    @DeleteMapping("/board/{id}/delete")
    public void deleteStudy(@PathVariable Long id) {
        boardService.deleteStudy(id);
    }

    // Study 탈퇴
    @DeleteMapping("/board/{cotudyid}/outstudy")
    public void outStudy(@PathVariable Long cotudyid){
        boardService.outStudy(cotudyid);
    }
}
