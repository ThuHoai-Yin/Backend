package com.example.demo.model;

import lombok.Data;
@Data
public class FileResponse {
    private String filename;
    private String extension;

    
	public FileResponse(String filename, String extension) {
		super();
		this.filename = filename;
		this.extension = extension;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	@Override
	public String toString() {
		return "FileResponse [filename=" + filename + ", extension=" + extension + "]";
	}
    
    
}
