package com.week06.cotudying_project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel(value = "회원가입 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해주세요", required = true, example = "phw1029")
    private String username;

    @ApiModelProperty(value = "비밀번호", required = true, example = "123456")
    private String password;

    //    @ApiModelProperty(value = "이메일", notes = "이메일 형식에 맞춰 작성해주세요", required = true, example = "xxx@gmail.com")
//    @NotBlank(message = "이메일을 입력해주세요")
//    @Pattern(regexp = "^[\\da-zA-Z]([-_.]?[\\da-zA-Z])*@[\\da-zA-Z]([-_.]?[\\da-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식이 아닙니다")
    @ApiModelProperty(value = "닉네임", notes = "닉네임을 입력해주세요", required = true, example = "닉네임입니다")
    private String nickname;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passwordCheck;


}
