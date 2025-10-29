package com.example.todo.thymeMember.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.thymeMember.dto.MemberDto;
import com.example.todo.thymeMember.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
