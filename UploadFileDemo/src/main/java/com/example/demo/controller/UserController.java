package com.example.demo.controller;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.InfoFileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.FileService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class UserController {

	/** User service */
	@Autowired
	private UserRepository userService;

	// ** provide jwt token */
	@Autowired
	private JwtTokenProvider tokenProvider;

	// ** Authentication manager */
	@Autowired
	AuthenticationManager authenticationManager;

	// ** Password encoder */
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FileService fileService;
	@Autowired
	private InfoFileRepository infoFileRepository;
	private final String infoCode = "employee";

	/**
	 * Create a user
	 * 
	 * @param user
	 * @return user
	 */
	@PostMapping
	public User createUser(@RequestBody User user) {

		Optional<User> findUser = userService.findById(user.getId());

		// check findUser have value, true: throw exception
		if (findUser.isPresent())
			throw new ValidationException("Id is existed!");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userService.save(user);

	}

	/**
	 * Update a user by id
	 * 
	 * @param user
	 */
	@PutMapping
	public void updateUserById(@RequestBody User user) {

		User findUser = userService.findById(user.getId())
				.orElseThrow(() -> new ValidationException("Id is not exist"));
		findUser = user;
		userService.save(findUser);

	}

	/**
	 * Delete user by id
	 * 
	 * @param id
	 */
	@DeleteMapping
	public User deleteUserById(@RequestParam Long id) {

		User findUser = userService.findById(id).orElseThrow(() -> new ValidationException("Id is not exist"));
		userService.delete(findUser);
		return findUser;

	}

	/**
	 * Login
	 * 
	 * @param LoginRequest
	 * @return string
	 */
	@PostMapping("/login")
	public String authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		// Authentication the information of user send request
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		// Set information of authentication in Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Send JWT to user
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		LoginResponse loginResponse = new LoginResponse(jwt);
		return loginResponse.toString();

	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file)
			throws IllegalStateException, IOException {
	    fileService.uploadFilẹ̣(file, infoCode);
		return new ResponseEntity<>("File uploaded!", HttpStatus.OK);
	}

}
