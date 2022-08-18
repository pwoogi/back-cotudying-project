package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.LoginRequestDto;
import com.week06.cotudying_project.dto.SignUpRequestDto;
import com.week06.cotudying_project.dto.TokenRequestDto;
import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.response.Response;
import com.week06.cotudying_project.service.AuthService;
import com.week06.cotudying_project.service.SignupCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.week06.cotudying_project.response.Response.success;

@Api(value = "Sign Controller", tags = "Sign")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final SignupCheckService signupCheckService;

    @ApiOperation(value = "회원가입", notes = "회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Response register(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
        return success();
    }

    //아이디 중복체크
    @GetMapping("/signup/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Response duplicateUsername(@PathVariable String username) {
        return Response.success(signupCheckService.checkMember(username));
    }

    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response logIn(@Valid @RequestBody LoginRequestDto req,
                                  HttpServletResponse response) {
        return Response.success(authService.logIn(req, response));
    }


    @ApiOperation(value = "토큰 재발급", notes = "토큰 재발급 요청")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reissue")
    public Response reissue(@RequestBody TokenRequestDto tokenRequestDto,
                            HttpServletResponse response) {
        return success(authService.reissue(tokenRequestDto, response));
    }

}
