package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class InfoFile {
	@Id
	private String id;
    @Column(name="max_size")
    private int maxSize;
    @Column
    private String typeFile;
    @Column
    private String extension;
    @Column
    private String path_file;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public String getTypeFile() {
		return typeFile;
	}
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getPath_file() {
		return path_file;
	}
	public void setPath_file(String path_file) {
		this.path_file = path_file;
	}
	@Override
	public String toString() {
		return "InfoFile [id=" + id + ", maxSize=" + maxSize + ", typeFile=" + typeFile + ", extension=" + extension
				+ ", path_file=" + path_file + "]";
	}
	
    
}
