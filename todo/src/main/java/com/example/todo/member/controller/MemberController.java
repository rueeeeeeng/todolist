package com.example.todo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.repository.MemberRepository;
import com.example.todo.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
	
	@Autowired
	MemberService memberService;

    MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
	
	@GetMapping("/memberlist")
	public String userlist(Model model) {
		List<MemberDto> list = memberService.memberList();
 		model.addAttribute("memberlist", list);
		return "members/memberlist";
	}
	
	@GetMapping("/signUp")
	public String signUp() {

		return "members/signUp";
	}
	
	@PostMapping("/joinUser")
	public String joinUser(MemberDto memberDto) {
		log.info("joinUser start");
		memberService.joinUser(memberDto);
		log.info("joinUser success");
		return "redirect:/members/memberlist";
	}
	
	@GetMapping("/updateForm/{memberId}")
	public String updateForm(@PathVariable("memberId") Long id, Model model){
		MemberDto member = memberService.selectMember(id);
		model.addAttribute("member", member);
		return "members/updateForm";
	}
	
	@PostMapping("/updateMember")
	public String updateMember(MemberDto memberDto) {
		memberService.updateMember(memberDto);
		return "redirect:/members/memberlist";
	}
	
}
	
	