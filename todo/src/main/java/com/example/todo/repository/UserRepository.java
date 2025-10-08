package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
