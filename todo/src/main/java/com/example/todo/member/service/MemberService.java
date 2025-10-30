package com.example.todo.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}

	public List<MemberDto> findAllMembers() {
		
		List<Member> members = memberRepository.findAll();
		List<MemberDto> memberlist = new ArrayList<MemberDto>();
		for (int i = 0; i < members.size(); i++) {
			memberlist.add(MemberDto.builder()
					.memberId(members.get(i).getMemberId())
					.memberName(members.get(i).getMemberName())
					.build());
		}
		return memberlist;
	}

	public MemberDto create(MemberDto createRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}


}
