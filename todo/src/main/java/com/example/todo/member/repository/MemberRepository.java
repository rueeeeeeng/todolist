package com.example.todo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member	, Long>{

}
