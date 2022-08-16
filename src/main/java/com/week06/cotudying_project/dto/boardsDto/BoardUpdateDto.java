package com.week06.cotudying_project.dto.boardsDto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoardUpdateDto {

    private String name;

    private String title;

    private String content;

    private Long num;

    private String category;

    private String startDate;

    private String endDate;

    private Long participant;

    private String registerStatus;

}
