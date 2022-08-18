package com.week06.cotudying_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupResultDto
{
    boolean result;

    public SignupResultDto(boolean checkedresult){
        this.result = checkedresult;
    }
}