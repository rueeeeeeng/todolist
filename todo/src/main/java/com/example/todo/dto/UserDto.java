package com.example.todo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private Long userId;
	
	private String username;
	
	private String password;
}
