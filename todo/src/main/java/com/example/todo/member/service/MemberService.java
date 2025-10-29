package com.example.todo.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true) //성능 최적화
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

	public MemberDto selectMember(Long id) {
		// TODO Auto-generated method stub
		Member mem = memberRepository.findById(id).get();
		MemberDto member = MemberDto.builder()
				.memberId(mem.getMemberId())
				.memberName(mem.getMemberName())
				.build();
		return member;
	}

	@Transactional
	public void updateMember(MemberDto memberDto) {
		Member member = memberRepository.findById(memberDto.getMemberId())
				.orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberDto.getMemberId()));

        // todo : 엔티티의 update 메소드에 passwordEncoder를 함께 전달
        member.update(memberDto.getMemberName(), 
                      memberDto.getPassword());
		log.info("=====MemberService Update End=====");
		
	}

	@Transactional
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
		log.info("=====MemberService Delete End=====");
		
	}
	
	
}
