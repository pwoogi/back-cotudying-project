package com.week06.cotudying_project.dto.boardsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardsShowRequestDto {
    private String title;
    private String category;
    private String content;
    private Long Participant;
}
