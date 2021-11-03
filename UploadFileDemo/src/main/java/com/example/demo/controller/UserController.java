package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.FileUpload;
import com.example.demo.model.InfoFile;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.impl.FileService;
import com.example.demo.service.impl.FileServiceCustomImp;
import com.example.demo.service.impl.InforFileService;
import com.example.demo.service.impl.RoleService;
import com.example.demo.service.impl.UserService;
import com.example.demo.login.*;
import com.example.demo.security.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class UserController {

	/** User service */
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

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
	private FileService fileService;
	@Autowired
	private FileServiceCustomImp customImp;
	@Autowired
	private InforFileService inforFileService;	
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

		//Authentication the information of user send request
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
	    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) throws IllegalStateException, IOException {
	    
	    	String infoCode="employee";
	    	FileUpload fileUp= new FileUpload();
	    	fileUp.setFilename(file.getOriginalFilename());
	    	List<FileUpload> checkExist=  fileService.findByFilename(fileUp.getFilename());
	    	if((!checkExist.isEmpty() )) {
	    		
	    		for (FileUpload fileUpload : checkExist) {
	    			if(fileUpload.getCode_info_file().equals(infoCode)) 
	    			{
	    			return new ResponseEntity<>("Filename is existed!", HttpStatus.BAD_REQUEST);
	    			}
				}
	    		   
	    	}
	    	Optional<InfoFile> infoFile = inforFileService.findById(infoCode);
	    	fileUp.setFilepath(infoFile.get().getPath_file()+file.getOriginalFilename());	
	        fileUp.setCode_info_file(infoCode);
	    	fileUp.setExtension(customImp.getExtension(file.getOriginalFilename()));
	    	fileUp.setId(customImp.createId("user"));
	    	fileUp.setFirst_update_date(new Date());
	    	fileService.save(fileUp);
	    	file.transferTo(new File(infoFile.get().getPath_file()+"\\"+file.getOriginalFilename()));
	    	return new ResponseEntity<>("File uploaded!", HttpStatus.OK);
	   }
	    

}
