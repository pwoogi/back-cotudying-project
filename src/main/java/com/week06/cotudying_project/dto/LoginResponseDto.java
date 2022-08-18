package com.week06.cotudying_project.dto;

import com.week06.cotudying_project.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String accessToken;


}
