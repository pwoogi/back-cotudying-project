package com.week06.cotudying_project.dto.boardsDto;


import com.week06.cotudying_project.model.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateResponse {

    private Long id;

    private Long registerUserId;

    private String title;

    private String content;

    private String registerStatus;

    private Long num;

    private String category;

    private String startDate;

    private String endDate;

    private Long participant;

    private List<Long> memberId;


}
