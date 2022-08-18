package com.week06.cotudying_project.dto.member;

import com.week06.cotudying_project.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {

    private Long id;
    private String username; // 로그인 아이디
    private String nickname; // 유저 닉네임

    public static MemberInfoDto toDto(Member member) {
        return new MemberInfoDto(member.getId(), member.getUsername(), member.getNickname());
    }

}