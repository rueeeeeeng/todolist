package com.example.todo.controller;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.UserDto;
import com.example.todo.dto.UserDto.UserDtoBuilder;
import com.example.todo.entity.User;
import com.example.todo.service.APIService;

@RestController
public class APIController {

	@Autowired
	private APIService apiService;
	
	//전통적인 자바 방식, 개발자가 직접 객체를 생성,관리하며, 매번 new로 객체를 만들면 메모리 낭비가 생길 수 있음
	//APIService apiService = new APIService(); 
	
	
	
	@GetMapping("/hello")
	public String helloAPI() {
		System.out.println("Hello world API!");
//		UserDto userDto = UserDto.builder()
//				.username("홍길동")
//				.password("1234")
//				.build();
//		
//		apiService.joinUser(userDto);
		
		List<UserDto> list = apiService.userlist();
		String username = list.get(0).getUsername();
 		System.out.println(username);
		return username;
	}
	
	@GetMapping("/")
	public String hello() {
		System.out.println("Hello world!");
		return "hello";
	}
}
