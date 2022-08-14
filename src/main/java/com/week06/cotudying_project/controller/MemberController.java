package com.week06.cotudying_project.controller;

import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.response.Response;
import com.week06.cotudying_project.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Member Controller", tags = "User")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원을 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public Response findAllUsers() {
        return Response.success(memberService.findAllUsers());
    }

    @ApiOperation(value = "개별 회원 조회", notes = "개별 회원을 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    public Response findUser(@ApiParam(value = "User ID", required = true) @PathVariable Long id) {
        return Response.success(memberService.findUser(id));
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원의 정보를 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{id}")
    public Response editUserInfo(@ApiParam(value = "User ID", required = true) @PathVariable Long id, @RequestBody MemberDto memberDto) {
        return Response.success(memberService.editUserInfo(id, memberDto));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴 시킴")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{id}")
    public Response deleteUserInfo(@ApiParam(value = "User ID", required = true) @PathVariable Long id) {
        memberService.deleteUserInfo(id);
        return Response.success();
    }
}
