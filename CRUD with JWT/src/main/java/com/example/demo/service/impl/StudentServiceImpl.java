package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {

   /** jdbcTemplate */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Student> getStudentsList() {
		
		String sql = "Select * from dbo.HOCVIEN";
		// query DB to get Student List
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
	
	}
	
	public Student searchStudentByName(@RequestBody String search) {
		
		String sql = "Select * from dbo.HOCVIEN where ten="+search;
		// query DB to get student by name
		return this.jdbcTemplate.queryForObject(sql, Student.class);
		
	}

	@Override
	public void deleteStudentById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudentByName(String name) {
		// TODO Auto-generated method stub
		
	}

}
