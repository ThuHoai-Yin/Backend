package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileResponse;
import com.example.demo.model.FileUpload;
import com.example.demo.model.InfoFile;

@Service
public interface FileService {

	/**
	 * Create extension for file
	 * 
	 * @param filename
	 * @return String
	 */
	public String getExtension(String filename);

	/**
	 * Create id for file
	 * 
	 * @param infoCode
	 * @return String
	 */
	public String createId(String infoCode);

	/**
	 * Find list file by info code
	 * 
	 * @param infoCode
	 * @return List<FileUpload>
	 */
	public List<FileUpload> findByInfoCode(String infoCode);

	/**
	 * Get list file response by info code
	 * 
	 * @param infoCode
	 * @return
	 */
	public List<FileResponse> getListFileByInfoCode(String infoCode);

	/**
	 * Delete file upload
	 * 
	 * @param filename
	 * @param infoCode
	 */
	public void deleteFile(String filename, String infoCode);

	/**
	 * Convert multiplepart file to file upload model
	 * 
	 * @param file
	 * @param infoCode
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public FileUpload convertMultipleToFileUpload(MultipartFile file, String infoCode)
			throws IllegalStateException, IOException;

	/**
	 * Upload file
	 * 
	 * @param file
	 * @param infoCode
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void uploadFile(List<MultipartFile> file, String infoCode) throws IllegalStateException, IOException;
}