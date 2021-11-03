package com.example.demo.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public interface UserService extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
