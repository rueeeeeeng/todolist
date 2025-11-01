package com.example.todo.member.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.dto.MemberUpdateRequestDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Transactional
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}

	
	public List<MemberDto> findAllMembers() {
		List<Member> members = memberRepository.findAll();
		List<MemberDto> memberlist = new ArrayList<MemberDto>();
		for (int i = 0; i < members.size(); i++) {
			memberlist.add(MemberDto.builder().memberId(members.get(i).getMemberId())
					.memberName(members.get(i).getMemberName()).build());
		}
		return memberlist;
	}

	@Transactional
	public MemberDto createMember(MemberDto createRequestDto) {
		// TODO : 요청 DTO와 응답 DTO로 나누기
		Member member = Member.builder().memberName(createRequestDto.getMemberName())
				.password(createRequestDto.getPassword()).build();
		Member savedMember = memberRepository.save(member);

		MemberDto memberDto = MemberDto.fromEntity(savedMember);
		return memberDto;
	}

	@Transactional
	public MemberDto updateMember(Long id, MemberUpdateRequestDto updateDto) {
		Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 없습니다: " + id));
		
		member.update(updateDto.getMemberName(), updateDto.getPassword());

		return MemberDto.fromEntity(member);
	}

}
