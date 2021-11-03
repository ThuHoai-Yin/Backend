package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Teacher;

public interface TeacherService {
	
	/** 
	 * get teacher list
	 * 
	 * @return List<Teacher>
	 */
	List<Teacher> getTeacherList();
	
	/**
	 * search teacher by name
	 * 
	 * @param name
	 * @return List<Teacher>
	 */
	List<Teacher> searchTeacherByName(String name);
	
	/**
	 * delete teacher by id
	 * 
	 * @param id
	 * @return String
	 */
	String deleteTeacherById(String id);
	
	/**
	 * delete teacher by name
	 * 
	 * @param name
	 * @return String
	 */
	String deleteTeacherByName(String name);
	
	/**
	 * update teacher by id
	 * 
	 * @param teacher
	 * @return String
	 */
	String updateById(Teacher teacher);
	
	/**
	 * create teacher
	 * 
	 * @param teacher
	 * @return String
	 */
	String createTeacher(Teacher teacher);
	
}
