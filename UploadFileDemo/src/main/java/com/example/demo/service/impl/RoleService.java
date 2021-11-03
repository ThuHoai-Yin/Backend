package com.example.demo.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;
@Repository
public interface RoleService extends JpaRepository<Role, Integer> {
	  Role findByName(String name);
}
