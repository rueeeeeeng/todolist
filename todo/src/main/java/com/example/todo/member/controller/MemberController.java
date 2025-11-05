package com.example.todo.member.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.dto.MemberUpdateRequestDto;
import com.example.todo.member.dto.SignUpRequestDto;
import com.example.todo.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @RestController : 이 클래스가 REST API 컨트롤러임을 나타내며, 
 * 메서드의 반환 값(객체)을 자동으로 JSON으로 변환
 */
// React 포트에서의 요청을 허용하기 위해 @CrossOrigin 추가
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/members") // API 기본 경로 설정
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 목록 조회 (GET /members)
     * @return List<MemberDto> (JSON 배열)
     */
    @GetMapping
    public List<MemberResponseDto> getAllMembers() {
    	log.info("GET /members 요청 수신");
        
        // @RestController List<MemberDto>를 JSON 배열로 변환해 응답
        return memberService.findAllMembers(); 
    }
    
    /**
     * 회원 상세조회 (GET /members/{id})
     * @param id 회원 ID (경로 변수)
     * @return ResponseEntity<MemberDto>
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable("id") Long id) {
    	log.info("GET /members/{} 요청 수신", id);
        MemberResponseDto memberDto = memberService.findMemberById(id);
        return ResponseEntity.ok(memberDto); // 200 OK와 함께 DTO 반환
    }

    /**
     * 회원 등록 (POST /members)
     * @param createRequestDto JSON Body를 DTO로 받음
     * @return ResponseEntity<MemberDto>
     */
    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(
            @Valid @RequestBody SignUpRequestDto createRequestDto) {
    	log.info("POST /members 요청 수신");
    	MemberResponseDto createdMember = memberService.createMember(createRequestDto);
        
        // 201 Created 상태 코드와 함께 생성된 리소스 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    /**
     * 회원 수정 (PUT /members/{id})
     * @param id 회원 ID(경로 변수)
     * @param updateRequestDto 수정할 회원 정보 (JSON Body)
     * @return ResponseEntity<MemberDto>
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable("id") Long id,
    		@RequestBody MemberUpdateRequestDto updateRequestDto) {
    	log.info("PUT /members/{} 요청 수신", id);
    	MemberResponseDto UpdatedMember = memberService.updateMember(id, updateRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(UpdatedMember);
	}
    
    /**
     * 회원 삭제 (DELETE /members/{id})
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
    	log.info("DELETE /members/{} 요청 수신", id);
    	memberService.deleteMember(id); 
        
        
        // 삭제 성공 시 204 No Content 반환
        return ResponseEntity.noContent().build();
    }
}