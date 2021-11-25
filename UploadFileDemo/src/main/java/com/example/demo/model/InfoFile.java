package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Data;
@Entity
@Data
public class InfoFile {
	@Id
	private String id;
    @Column(name="max_size")
    private int maxSize;
    @Column
    private String extension;
    @Column(name="path_file")
    private Path pathFile;
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
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Path getPathFile() {
		return pathFile;
	}
	public void setPathFile(Path pathFile) {
		this.pathFile = pathFile;
	}
	@Override
	public String toString() {
		return "InfoFile [id=" + id + ", maxSize=" + maxSize  + ", extension=" + extension
				+ ", path of file=" + pathFile + "]";
	}
	
    
}
