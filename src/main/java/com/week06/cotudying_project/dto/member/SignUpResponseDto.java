package com.week06.cotudying_project.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpResponseDto {
    boolean result;

    public SignUpResponseDto(boolean checkedresult){
        this.result = checkedresult;
    }
}
