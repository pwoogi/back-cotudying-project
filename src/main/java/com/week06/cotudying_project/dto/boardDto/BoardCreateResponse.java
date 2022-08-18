package com.week06.cotudying_project.dto.boardDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
public class BoardCreateResponse {
    private int id;
    private String title;
    private String content;

}
