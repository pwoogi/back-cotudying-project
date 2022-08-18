package com.week06.cotudying_project.validation;

import com.week06.cotudying_project.dto.SignUpRequestDto;

public class SignupValidation {
    public static boolean validationSignupInput(SignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //username 확인
        //영어 대소문자, 숫자로 3~15자
//        if (!username.matches("^[a-zA-Z0-9]{3,15}$")) {
//            throw new IllegalArgumentException("username 조건이 맞지 않습니다.");
//        }

        //password 확인
        //숫자, 영어 소문자, 특수문자 포함 8~15자 이내
        if(!password.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")) {
            throw new IllegalArgumentException("8~15자리의 영문,숫자,특수문자포함입니다.");
        }

        if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 username을 사용할 수 없습니다.");
        }

        return true;
    }
}
