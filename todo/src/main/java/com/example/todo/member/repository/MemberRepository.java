package com.example.todo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.member.dto.MemberDto;
import com.example.todo.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
