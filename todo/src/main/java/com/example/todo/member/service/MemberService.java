package com.example.todo.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.dto.MemberUpdateRequestDto;
import com.example.todo.member.dto.SignUpRequestDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	/**
	 * 조회 서비스
	 */
	public List<MemberResponseDto> findAllMembers() {
		List<Member> members = memberRepository.findAll();
		
		return members.stream().map(MemberResponseDto::fromEntity)
				.collect(Collectors.toList());
	}
	

	/**
	 * 멤버 삭제 서비스
	 * @param id
	 */
	@Transactional
	public void deleteMember(Long id) {
		
		if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("삭제할 멤버가 없습니다. ID: " + id);
        }
		memberRepository.deleteById(id);
	}

	/**
	 * 멤버 생성 서비스
	 * @param createRequestDto
	 * @return
	 */
	@Transactional
	public MemberResponseDto createMember(SignUpRequestDto createRequestDto) {
		//TODO : 비밀번호 암호화
		Member member = Member.builder().memberName(createRequestDto.getMemberName())
				.password(createRequestDto.getPassword()).build();
		Member savedMember = memberRepository.save(member);

		MemberResponseDto memberDto = MemberResponseDto.fromEntity(savedMember);
		return memberDto;
	}

	/**
	 * 멤버 수정 서비스
	 * @param id
	 * @param updateDto
	 * @return
	 */
	@Transactional
	public MemberResponseDto updateMember(Long id, MemberUpdateRequestDto updateDto) {
		Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 없습니다: " + id));
		
		member.update(updateDto.getMemberName(), updateDto.getPassword());

		return MemberResponseDto.fromEntity(member);
	}

	/**
	 * 멤버 상세 조회 서비스
	 * @param id
	 * @return
	 */
	public MemberResponseDto findMemberById(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 없습니다.")); 
		
		return MemberResponseDto.fromEntity(member);
	}

}
