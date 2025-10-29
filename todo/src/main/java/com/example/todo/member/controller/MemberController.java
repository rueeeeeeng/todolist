package com.example.todo.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.List;

// (주의!) React 포트(예: 3000)에서의 요청을 허용하기 위해 @CrossOrigin 추가
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/members") // API 기본 경로 설정
@RequiredArgsConstructor // (Lombok)
public class MemberController {

    private final MemberService memberService;

    /**
     * 1. 회원 목록 조회 (GET /members)
     * @return List<MemberResponseDto> (JSON 배열)
     */
    @GetMapping
    public List<MemberDto> getAllMembers() {
        // memberService에서 DTO 리스트를 반환하도록 구현
        return memberService.findAllMembers(); 
    }

    /**
     * 2. 회원 삭제 (DELETE /members/{id})
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id); // (기존에 만든 서비스 재활용)
        
        // 삭제 성공 시 200 OK 또는 204 No Content 반환
        return ResponseEntity.ok().build(); 
        // return ResponseEntity.noContent().build(); // 204도 좋은 선택
    }

    /**
     * 3. 회원 등록 (POST /members)
     * @param createRequestDto JSON Body를 DTO로 받음
     * @return MemberResponseDto (생성된 멤버 정보)
     */
    @PostMapping
    public ResponseEntity<MemberDto> createMember(
            @RequestBody MemberDto createRequestDto) {
        
    	MemberDto createdMember = memberService.create(createRequestDto);
        
        // 201 Created 상태 코드와 함께 생성된 리소스 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    // ... (회원 상세 조회, 회원 수정 API) ...
}