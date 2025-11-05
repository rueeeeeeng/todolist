package com.example.todo.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String memberName;
	@NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;   
}
