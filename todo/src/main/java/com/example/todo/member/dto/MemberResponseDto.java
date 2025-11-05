package com.example.todo.member.dto;

import com.example.todo.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
	private Long memberId;
	private String memberName;
	
	
	/**
     * Entity를 DTO로 변환하는 정적(static) 팩토리 메서드
     * @param member Member 엔티티
     * @return MemberDto
     */
	public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .build();
    }
}
