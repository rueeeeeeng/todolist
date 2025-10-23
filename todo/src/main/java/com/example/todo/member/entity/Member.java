package com.example.todo.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 이 클래스를 DB 테이블과 매핑
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가 전략 (MySQL 기준)
	@Column(name = "memberId")
	private Long memberId;

	@Column(nullable = false, length = 50)
	private String memberName;

	@Column(nullable = false)
	private String password;

	@Builder
	public Member(String memberName, String password) {
		this.memberName = memberName;
		this.password = password;
	}

	public void update(String newMemberName, String newPassword) {
		this.memberName = newMemberName;
		if (newPassword != null && !newPassword.isEmpty()) {
			// 엔티티가 직접 비밀번호를 암호화하여 저장
			this.password = newPassword;
		}
	}
}
