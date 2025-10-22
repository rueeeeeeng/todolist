package com.example.todo.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	public List<MemberDto> memberList() {
		List<Member> userlist = memberRepository.findAll();
		List<MemberDto> userlistDtos = new ArrayList<MemberDto>();
		for (Member member : userlist) {
			// Entity를 DTO로 변환하여 리스트에 추가 (응답용이므로 비밀번호는 제외)
			MemberDto memberDto = MemberDto.builder()
					.memberId(member.getMemberId()) // ID 추가 (DB에서 생성된 값)
					.memberName(member.getMemberName()).build();
			userlistDtos.add(memberDto);
		}

		return userlistDtos;
	}

	public void joinUser(MemberDto memberDto) {
		Member member = Member.builder()
				.memberName(memberDto.getMemberName())
				.password(memberDto.getPassword())
				.build();
		memberRepository.save(member);
		log.info("userService joinUser success");
	}
}
