package com.example.demo.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ExceptionCustom;
import com.example.demo.model.FileUpload;
import com.example.demo.model.InfoFile;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.InfoFileService;
@Service
public class InfoFileServiceImp implements InfoFileService{

	@Autowired
	public FileRepository fileRepository;
	@Override
	public void checkFile(FileUpload file, InfoFile inforFile) {
		if(!inforFile.getExtension().contains(file.getExtension())) throw new ExceptionCustom("Error extension");
		if(file.getSize()>inforFile.getMaxSize()) throw new ExceptionCustom("Too lagre");
		if(checkExit(file, inforFile.getId())) throw new ExceptionCustom("File existed!");
		
	}

	@Override
	public boolean checkExit(FileUpload file, String infoCode) {
		List<FileUpload> findFileExist=fileRepository.findByFilename(file.getFilename());
		for (FileUpload fileUpload : findFileExist) {
			if(fileUpload.getCode_info_file().equals(infoCode)) {
				return true;
			}
		}
		return false;
	}

}
