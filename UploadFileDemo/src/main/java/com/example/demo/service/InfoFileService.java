package com.example.demo.service;

import com.example.demo.model.FileUpload;
import com.example.demo.model.InfoFile;

public interface InfoFileService {
	
	/**
	 * Check valid file
	 * 
	 * @param file
	 * @param inforFile
	 */
	public void checkFile(FileUpload file, InfoFile inforFile);

	/**
	 * Check file existed
	 * 
	 * @param file
	 * @param infoCode
	 * @return boolean
	 */
	public boolean checkExit(FileUpload file, String infoCode);

}
