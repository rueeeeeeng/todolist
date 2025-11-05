package com.example.todo.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String memberName; // 수정할 이름
    private String password;   // 수정할 비밀번호
}