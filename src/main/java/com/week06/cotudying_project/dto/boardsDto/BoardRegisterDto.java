package com.week06.cotudying_project.dto.boardsDto;

import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.model.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRegisterDto {

    private String name;

    private String title;

    private String content;

    private Long num;

    private MemberDto memberDto;

    private String category;

    private String startPeriod;

    private String finishPeriod;

    private Long participant;


}