package com.example.todo.thymeMember.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.thymeMember.dto.MemberDto;
import com.example.todo.thymeMember.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("thymeleaf/members")
public class MemberController {

	@Autowired
	MemberService memberService;

	
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
	
	@DeleteMapping("/deleteMember/{memberId}")
	public String deleteMember(@PathVariable("memberId") Long id) {
		log.info("=====================>"+id);
		memberService.deleteMember(id);
		return "redirect:/members/memberlist";
	}
	
}
	
	