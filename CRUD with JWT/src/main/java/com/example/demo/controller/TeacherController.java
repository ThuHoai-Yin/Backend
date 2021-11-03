package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;

@RestController
@RequestMapping(path = "/api/teacher")
public class TeacherController {

	/** teacherService */
	@Autowired
	private TeacherService teacherService;

	/**
	 * Search teacher by Name
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping
	public List<Teacher> searchTeacherByName(String name) {
		return teacherService.searchByName(name);
	}

	/**
	 * delete teacher by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping
	public String deleteTeacherById(String id) {
		return teacherService.deleteById(id);
	}

	@DeleteMapping(path = "/name")
	public String delTeacherByName(String name) {
		return teacherService.deleteByName(name);
	}

	@PutMapping
	public String updtTeacherById(@RequestBody Teacher teacher) {
		return teacherService.updateById(teacher);
	}

	/**
	 * create teacher by model
	 * 
	 * @param teacher
	 * @return
	 */
	@PostMapping
	public String createTeacher(@RequestBody Teacher teacher) {
		String teacherName= "hoai";
		
		// check <<condition>> is ...
		if (true) {
			// handle true: return string
			return "hoai cute";
		}else { // handle false: return string
			return "hoai dang iu";
		}

		return teacherService.createTeacher(teacher);
	}
}
