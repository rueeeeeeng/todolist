package com.example.todo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 데이터 조회(읽기)에 필요
@Setter // 폼 데이터 바인딩(쓰기)에 필요
@NoArgsConstructor //Spring이 기본 객체를 생성하기 위해 필요
@Builder // DB 저장 전 수동으로 객체를 만들 때 유용하므로 유지
@AllArgsConstructor // @Builder를 위해 필요하므로 함께 유지

public class MemberDto {
	private Long memberId;
	
	private String memberName;
	
	private String password;
}
