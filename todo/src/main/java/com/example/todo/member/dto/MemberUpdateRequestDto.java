package com.example.todo.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String memberName; // 수정할 이름
    private String password;   // 수정할 비밀번호
}