package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FileUpload;
@Service
public class FileServiceCustomImp implements FileServiceCustom{
	@Autowired
	private FileService fileService;
    private static int countFile;
	@Override
	public String getExtension(String filename) {
		for(int i=filename.length()-1;i>0;i--) {
			if(filename.charAt(i)=='.') {
				return filename.substring(i+1);
			}
		}
		return null;
	}

	@Override
	public String createId(String infoCode) {
		String id =null;
		 do {
		    id =Integer.toString(countFile);
			while(id.length()<5) {
				id='0'+id;
			}
			id=infoCode+id;
			countFile++;
		} while(!fileService.findById(id).isEmpty());
		return id;
		
	}

	@Override
	public boolean checkExisted(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		return false;
	}
       
	
}
