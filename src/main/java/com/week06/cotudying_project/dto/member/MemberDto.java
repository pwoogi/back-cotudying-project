package com.week06.cotudying_project.dto.member;

import com.week06.cotudying_project.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username; // 로그인 아이디
    private String nickname;

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getUsername(), member.getNickname());
    }

}
