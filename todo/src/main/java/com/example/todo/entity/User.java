package com.example.todo.entity;

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

@Entity// 이 클래스를 DB 테이블과 매핑
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 사용을 위한 기본 생성자
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// ID 자동 증가 전략 (MySQL 기준)
	@Column(name = "userId")
	private Long userId;
	
	@Column(nullable = false, length = 50)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
