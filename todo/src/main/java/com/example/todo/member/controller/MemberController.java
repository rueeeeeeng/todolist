package com.example.todo.member.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * @RestController : 이 클래스가 REST API 컨트롤러임을 나타내며, 
 * 메서드의 반환 값(객체)을 자동으로 JSON으로 변환해 줍니다.
 */
// React 포트(예: 3000)에서의 요청을 허용하기 위해 @CrossOrigin 추가
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/members") // API 기본 경로 설정
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 1. 회원 목록 조회 (GET /members)
     * @return List<MemberResponseDto> (JSON 배열)
     */
    @GetMapping
    public List<MemberDto> getAllMembers() {
    	// 1단계: 가짜(Mock) 데이터 생성
        // (DB 연동 대신 임시 데이터를 만듭니다)
        System.out.println("GET /api/members 요청 수신!");
        
        // 2단계: 가짜 데이터 반환
        // @RestController가 이 List<MemberDto>를 JSON 배열로 변환해 응답합니다.
        return memberService.findAllMembers(); 
    }

    /**
     * 2. 회원 삭제 (DELETE /members/{id})
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id); 
        
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