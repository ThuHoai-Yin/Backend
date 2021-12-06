package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

@Service
public interface UserService extends UserDetailsService {

	/**
	 * Add role to user
	 * 
	 * @param user
	 * @param role
	 * @return
	 */
	public User addRole(User user, Role role);

	/**
	 * Load user by id
	 * 
	 * @param id
	 * @return UserDetail
	 */
	public UserDetails loadUserById(Long id);
}
