package com.week06.cotudying_project.dto.boardsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardsDetailRequestDto {
    private String title;
    private String category;
    private Long num;
    private String period;
    private String content;
    private Long participant;
}
