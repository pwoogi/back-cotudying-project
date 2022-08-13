package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.RegisterDto;
import com.week06.cotudying_project.response.Response;
import com.week06.cotudying_project.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.week06.cotudying_project.response.Response.success;

@RequiredArgsConstructor
@RestController("/api")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "회원가입", notes="회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Response register(@RequestBody RegisterDto registerDto) {
        return success(authService.register(registerDto));
    }
}
