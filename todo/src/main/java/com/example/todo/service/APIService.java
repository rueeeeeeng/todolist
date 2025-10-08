package com.example.todo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class APIService {

	@Autowired
	UserRepository userRepository;

	@Transactional // DB 작업을 하나의 단위로 묶습니다.
	public Long joinUser(UserDto userdto) {

		// 1. DTO -> Entity 변환 (Service의 책임)
		User newUser = User.builder()
				.username(userdto.getUsername())
				.password(userdto.getPassword())
				.build();

		// 2. Repository에 Entity를 전달하여 저장(INSERT) 요청
		User savedUser = userRepository.save(newUser);

		// 3. 저장된 Entity의 ID를 반환 (생성 완료를 알림)
		return savedUser.getUserId();
	}

	public List<UserDto> userlist() {
		List<User> userlist = userRepository.findAll();
		List<UserDto> userlistDtos = new ArrayList<UserDto>();
		for (User user : userlist) {
			// Entity를 DTO로 변환하여 리스트에 추가 (응답용이므로 비밀번호는 제외)
			UserDto userDto = UserDto.builder()
					.userId(user.getUserId()) // ID 추가 (DB에서 생성된 값)
					.username(user.getUsername()).build();
			userlistDtos.add(userDto);
		}

		return userlistDtos;
	}
}
