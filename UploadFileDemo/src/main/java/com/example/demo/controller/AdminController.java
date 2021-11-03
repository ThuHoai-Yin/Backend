package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
import com.example.demo.service.impl.FileServiceCustom;
import com.example.demo.service.impl.FileServiceCustomImp;
import com.example.demo.service.impl.InforFileService;
import com.example.demo.service.impl.RoleService;
import com.example.demo.service.impl.UserService;
import com.example.demo.service.impl.UserServiceCustom;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")

public class AdminController {

	//** User service custom */
	@Autowired
	private UserServiceCustom userServiceCustom;
	//** User service */
	@Autowired
	private UserService userService;
	//** User role service */
	@Autowired
	private RoleService roleService;
	@Autowired
	private FileService fileService;
	@Autowired
	private FileServiceCustomImp customImp;
	@Autowired
	private InforFileService inforFileService;	
	/**
	 * Get all  users
	 * 
	 * @return List<User>
	 */
	@GetMapping("/getUser")
	public List<User> findAllUser(){
    	return userService.findAll();
	}
	
	/**
	 * Get all role 
	 * 
	 * @return List<User>
	 */
	@GetMapping("/getRole")
	public List<Role> findAllRole(){
		return roleService.findAll();
	}
	
    /**
     * Create role
     * 
     * @param role
     * @return role
     */
    @PutMapping
    public Role createRole(@RequestBody Role role) {
    	return roleService.save(role);
    }
    
    /**
     * Add role for user
     * 
     * @param username
     * @param rolename
     * @return User
     */
    @PostMapping
    public CustomUserDetails addRoletoUser(@RequestParam String username, @RequestParam String rolename) {

    	CustomUserDetails user= (CustomUserDetails) userServiceCustom.loadUserByUsername(username);
      Role role=roleService.findByName(rolename);
      user.addRoleToUser(role);
      userService.save(user.getUser());
		return user;
    	
    }
    
    /**
     * Update role
     * 
     * @param id
     * @param role
     * @return Role
     */
    @PostMapping("/updateRole")
    public Role updateRole(@RequestParam int id, @RequestBody Role role) {
    	Role findRole=roleService.getById(id);
    	findRole=role;
    	return roleService.save(findRole);
    	
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody  MultipartFile fileUploaded) throws IllegalStateException, IOException {
    
    	String infoCode="admin";
    	FileUpload fileUp= new FileUpload();
    	fileUp.setFilename(fileUploaded.getOriginalFilename());
    	List<FileUpload> checkExist=  fileService.findByFilename(fileUp.getFilename());
    	if((checkExist!=null )) {
    		for (FileUpload fileUpload : checkExist) {
    			if(fileUpload.getCode_info_file().equals(infoCode)) {
    			return new ResponseEntity<>("Filename is existed!", HttpStatus.BAD_REQUEST);}
			}
    		   
    	}
        fileUp.setCode_info_file(infoCode);
    	fileUp.setExtension(customImp.getExtension(fileUploaded.getOriginalFilename()));
    	fileUp.setId(customImp.createId("admin"));
    	Optional<InfoFile> infoFile = inforFileService.findById(infoCode);
    	fileUp.setFilepath(infoFile.get().getPath_file()+fileUploaded.getOriginalFilename());	
    	fileUp.setFirst_update_date(new Date());
    	fileService.save(fileUp);
    	fileUploaded.transferTo(new File("D:\\Spring Boot\\FileServer\\"+fileUploaded.getOriginalFilename()));
    	return new ResponseEntity<>("File uploaded!", HttpStatus.OK);
   }
    
    @PostMapping("/overrideFile")
    public ResponseEntity<String> overrideFile(@RequestBody  MultipartFile fileUploaded) throws IllegalStateException, IOException {
    	FileUpload fileUp= new FileUpload();
    	fileUp.setFilename(fileUploaded.getOriginalFilename());
    	List<FileUpload> checkExist=  fileService.findByFilename(fileUp.getFilename());
    	for (FileUpload fileUpload : checkExist) {
    			if(fileUpload.getCode_info_file().equals("admin")) {
    			fileUp=fileUpload;
    			fileUp.setFirst_update_date(new Date());
    			fileService.save(fileUp);
    			fileUploaded.transferTo(new File("D:\\Spring Boot\\FileServer\\"+fileUploaded.getOriginalFilename()));
    	    	return new ResponseEntity<>("Override file success!", HttpStatus.OK);
			}
    	}
    	
    	return new ResponseEntity<>("Don't find file to override!", HttpStatus.BAD_REQUEST);
   }
    
    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam List<String> filename) throws IllegalStateException, IOException {
    	for (String fileName : filename) {
    		List<FileUpload> checkExist= fileService.findByFilename(fileName);
        	for (FileUpload fileUpload : checkExist) {
    			if(fileUpload.getCode_info_file().equals("admin")) {
    			fileService.delete(fileUpload);
    	    	return new ResponseEntity<>("Delete Success!", HttpStatus.OK);
    		}
		}
	}	
	return new ResponseEntity<>("Filename is not existed!", HttpStatus.BAD_REQUEST);
   }
    
}
