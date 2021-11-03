package com.example.demo.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class FileUpload {
    
	 @Column
	private String code_info_file;
	@Id
	private String id;
    @Column
    private String filename;
    @Column
    private String filepath;
    @Column
    private String extension;
    @Column 
    private Date first_update_date;

	public String getCode_info_file() {
		return code_info_file;
	}
	public void setCode_info_file(String code_info_file) {
		this.code_info_file = code_info_file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Date getFirst_update_date() {
		return first_update_date;
	}
	public void setFirst_update_date(Date first_update_date) {
		this.first_update_date = first_update_date;
	}
	@Override
	public String toString() {
		return "FileUpload [code_info_file=" + code_info_file + ", id=" + id + ", filename=" + filename + ", filepath="
				+ filepath + ", extension=" + extension + ", first_update_date=" + first_update_date + "]";
	}
    

}
