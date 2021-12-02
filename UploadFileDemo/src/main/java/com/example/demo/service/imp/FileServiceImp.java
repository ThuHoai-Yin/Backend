package com.example.demo.service.imp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.ExceptionCustom;
import com.example.demo.model.FileResponse;
import com.example.demo.model.FileUpload;
import com.example.demo.model.InfoFile;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.InfoFileRepository;
import com.example.demo.service.FileService;

@Service
public class FileServiceImp implements FileService {
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private InfoFileRepository infoFileRepository;
	private static int countFile;

	@Override
	public String getExtension(String filename) {
		for (int i = filename.length() - 1; i > 0; i--) {
			if (filename.charAt(i) == '.') {
				return filename.substring(i + 1);
			}
		}
		return null;
	}

	@Override
	public String createId(String infoCode) {
		String id = null;
		do {
			id = Integer.toString(countFile);
			while (id.length() < 5) {
				id = '0' + id;
			}
			id = infoCode + id;
			countFile++;
		} while (!fileRepository.findById(id).isEmpty());
		return id;

	}

	@Override
	public boolean checkExisted(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FileResponse> getListFileByInfoCode(String infoCode) {
		List<FileUpload> findFile = findByInfoCode(infoCode);
		List<FileResponse> result = new ArrayList<>();
		for (FileUpload fileUpload : findFile) {
			result.add(new FileResponse(fileUpload.getFilename(), fileUpload.getExtension()));
		}
		return result;
	}

	@Override
	public void deleteFile(String filename, String infoCode) {

		List<FileUpload> checkExist = fileRepository.findByFilename(filename);
		for (FileUpload file : checkExist) {
			// check filename có trong page ko
			if (file.getCode_info_file().equals(infoCode)) {
				fileRepository.delete(file);
				return;
			}

		}
		new ExceptionCustom("Can't delete file");
	}

	@Override
	public boolean uploadFilẹ̣(MultipartFile file, String infoCode) throws IOException {
		FileUpload fileUp = new FileUpload();
		file.transferTo(new File("D:\\Spring Boot\\FileServer\\" + file.getOriginalFilename()));
		fileUp.setFilename(file.getOriginalFilename());
		List<FileUpload> checkExist = fileRepository.findByFilename(fileUp.getFilename());
		if ((checkExist != null)) {
			for (FileUpload fileUpload : checkExist) {
				if (fileUpload.getCode_info_file().equals(infoCode)) throw new ExceptionCustom("File is exited!");
			}
		}
		fileUp.setCode_info_file(infoCode);
		fileUp.setExtension(getExtension(file.getOriginalFilename()));
		fileUp.setId(createId( infoCode));
	    InfoFile infoFile=infoFileRepository.getById(infoCode);
		fileUp.setFilepath(infoFile.getPathFile() + file.getOriginalFilename());
		fileRepository.save(fileUp);
		return true;
	}

	@Override
	public List<FileUpload> findByInfoCode(String infoCode) {
		List<FileUpload> file = fileRepository.findAll();
		List<FileUpload> result=new ArrayList<>();
		for (FileUpload fileUpload : file) {
			if (fileUpload.getCode_info_file().equals(infoCode)) {
				result.add(fileUpload);
			}
		}
		return result;
	}


}
