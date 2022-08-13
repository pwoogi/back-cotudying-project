package com.week06.cotudying_project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "회원가입 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해주세요", required = true, example = "phw1029")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @ApiModelProperty(value = "비밀번호", required = true, example = "123456")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @ApiModelProperty(value = "이메일", notes = "이메일 형식에 맞춰 작성해주세요", required = true, example = "xxx@gmail.com")
    @NotBlank(message = "이메일을 입력해주세요")
    @Size(min=2, message = "올바른 이메일 형식이 아닙니다") //TODO : 정규식 찾아보자
    private String email;



}
