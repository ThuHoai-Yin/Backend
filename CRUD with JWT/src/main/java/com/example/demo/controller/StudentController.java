package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public List<Student> getStudent() {
		return studentService.getStudentsList();
	}

}
