package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileUpload;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.InfoFileRepository;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
//	  @PostMapping("/upload")
//	    public String uploadFile(@RequestBody MultipartFile file,String infoCode) throws IllegalStateException, IOException {
//	    
//			FileUpload fileUp= new FileUpload();
//	    	fileUp.setFilename(file.getOriginalFilename());
//	    	FileUpload checkExist= fileService.findByFilename(fileUp.getFilename());
//	    	if(checkExist!=null) {
//	    		return "Error!Filename is existed!";
//	    	}
//	    	fileUp.setFilepath("D:\\Spring Boot\\FileServer\\"+file.getOriginalFilename());
//	        if(inforFileService.findById(infoCode).isEmpty()) {
//	        	return "Inforcode wrong";
//	        }
//	        fileUp.setCode_info_file(infoCode);
//	    	fileUp.setExtension(customImp.getExtension(file.getOriginalFilename()));
//	    	fileUp.setId(customImp.createId("admin"));
//	    	fileService.save(fileUp);
//	    	file.transferTo(new File("D:\\Spring Boot\\FileServer\\"+file.getOriginalFilename()));
//	    	return "Success!";
//	   }
//	    
//	    @PostMapping("/file")
//	    public String overrideFile(@RequestBody MultipartFile file) throws IllegalStateException, IOException {
//	    
//	    	FileUpload fileUp= new FileUpload();
//	    	fileUp.setFilename(file.getOriginalFilename());
//	    	FileUpload checkExist= fileService.findByFilename(fileUp.getFilename());
//	    	fileService.delete(checkExist);
//	    	fileUp.setId(checkExist.getId());
//	    	fileUp.setFilepath("D:\\Spring Boot\\FileServer\\"+file.getOriginalFilename());
//	    	fileService.save(fileUp);
//	    	file.transferTo(new File("D:\\Spring Boot\\FileServer\\"+file.getOriginalFilename()));
//	    	return "Success!";
//	   }
//	    
//	    @DeleteMapping("/file")
//	    public String deleteFile(@RequestBody MultipartFile file) throws IllegalStateException, IOException {
//	    
//	    	FileUpload fileUp= new FileUpload();
//	    	fileUp.setFilename(file.getOriginalFilename());
//	    	FileUpload checkExist= fileService.findByFilename(fileUp.getFilename());
//	    	if(checkExist==null) {
//	    		return "File not existed!";
//	    	}
//	    	fileService.delete(checkExist);
//	    	return "Success!";
//	   }
}
