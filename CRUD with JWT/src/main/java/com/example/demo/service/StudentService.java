package com.example.demo.service;
import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	/** 
	 * get list of student
	 * 
	 * @return List<Student>
	 */
	List<Student> getStudentsList();
	
	/** 
	 * search student by name
	 * 
	 * @param search
	 * @return Student
	 */
	Student searchStudentByName(String search);
	
	/**
	 *  delete student by Id
	 *  
	 * @param id
	 */
	void deleteStudentById(String id);
	
	/**
	 *  delete student by name
	 *  
	 *  @param name
	 */
	void deleteStudentByName(String name);
	
}
