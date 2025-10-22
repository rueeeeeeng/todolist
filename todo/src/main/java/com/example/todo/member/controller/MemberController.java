package com.example.todo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/users")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/userlist")
	public String userlist(Model model) {
		List<MemberDto> list = memberService.memberList();
		log.info(list.get(0).getMemberName());
 		model.addAttribute("memberlist", list);
		return "users/userlist";
	}
	
	@GetMapping("/signUp")
	public String signUp() {

		return "users/signUp";
	}
	
	@PostMapping("/joinUser")
	public String joinUser(MemberDto memberDto) {
		log.info("joinUser start");
		memberService.joinUser(memberDto);
		log.info("joinUser success");
		return "redirect:/users/userlist";
	}
}
