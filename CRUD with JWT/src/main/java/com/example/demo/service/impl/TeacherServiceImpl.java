package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Teacher> getTeacherList() {
	
		String sql = "Select * from dbo.GIAOVIEN";
		// query db to get teacher list
	    return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Teacher.class));
	
	}

	@Override
	public List<Teacher> searchByName(String name) {
		
		//check name is empty, true: return all list
		if (name.isEmpty()) return getTeacherList();
			
		String sql = "Select * from dbo.GIAOVIEN where HOTEN=?";
		// query db to get teacher list by name 
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Teacher.class), name);
	
	}

	@Override
	public String deleteTeacherById(String id) {
		
		// check id is empty, true: return warning
		if (id.isEmpty()) return "Id is empty";
		
		String sql = "DELETE FROM GiangDay WHERE MAGV ='" + id + "' Delete from GiaoVien where MaGV='" + id + "'";
		// execute sql to delete teacher by id
		jdbcTemplate.execute(sql);
		return "Delete success";

	}

	@Override
	public String deleteTeacherByName(String name) {
		
		// check name is empty, true: return strings
		if (name.isEmpty())
			return "Name is a blank";
		
		String sql = "DELETE FROM GIANGDAY WHERE MAGV in (select MAGV from GIAOVIEN where hoten='" + name
				+ "') Delete from GIAOVIEN where hoten='" + name + "'";
		// execute sql to delete teacher by name
		jdbcTemplate.execute(sql);
		return "Delete success";

	}

	@Override
	public String updateTeacherById(Teacher teacher) {
		
		// check teacher is null, true: return strings
		if (teacher==null) return "Data is null";
		
		String sql="Update GIAOVIEN "
				+ "SET hoten='"+teacher.getHoTen()+"' , "
				+ "hocvi='"+teacher.getHocVi()
				+"', hocham='"+teacher.getHocHam()
				+"',gioitinh='"+teacher.getGioiTinh()
				+"', ngsinh='"+teacher.getNgaySinh()
				+"' ,ngvl='"+teacher.getNgVL()
				+"',heso="+teacher.getHeSo()
				+",mucluong="+teacher.getMucLuong()
				+",makhoa='"+teacher.getMaKhoa()+"' "
				+ "where MAGV='"+teacher.getMaGV()+"'";
		// execute sql to update teacher by Id
		jdbcTemplate.execute(sql);
		return "Update succedd!";
		
	}

	@Override
	public String createTeacher(Teacher teacher) {
		
		//check teacher = null, true: return String
		if (teacher==null) return "Data is null";
		
		String sql="insert into GIAOVIEN "
				+ "values('"+teacher.getMaGV()+"','"
				+ teacher.getHoTen()+" ',' "
				+ teacher.getHocVi()+"',' "
			    + teacher.getHocHam()+"','"
				+ teacher.getGioiTinh()+"','"
			    + teacher.getNgaySinh()+"','"
				+ teacher.getNgVL()+"',"
			    + teacher.getHeSo()+","
			    + teacher.getMucLuong()+",'"
			    + teacher.getMaKhoa()+"' )";
		// execute sql to create teacher
		jdbcTemplate.execute(sql);
		return "Create succeed!";
	
	}

}
