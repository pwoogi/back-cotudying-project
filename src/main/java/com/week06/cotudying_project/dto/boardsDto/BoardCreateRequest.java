package com.week06.cotudying_project.dto.boardsDto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiOperation(value = "게시글 생성 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequest {

    @NotBlank(message = "작성자를 입력해주세요.")
    private String name;

    @ApiModelProperty(value = "게시글 제목", notes = "게시글 제목을 입력해주세요.", required = true, example = "게시글 제목")
    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;

    @ApiModelProperty(value = "게시글 본문", notes = "게시글 본문을 입력해주세요.", required = true, example = "게시글 본문")
    @NotBlank(message = "게시글 본문을 입력해주세요.")
    private String content;

    @NotBlank(message = "원하시는 모집 인원수를 입력해주세요")
    private Long num;

    @NotBlank(message = "언어를 선택해주세요")
    private String category;

    @NotBlank(message = "모집 시작기간을 선택해주세요")
    private String startDate;

    @NotBlank(message = "모집 종료기간을 선택해주세요")
    private String endDate;




}
