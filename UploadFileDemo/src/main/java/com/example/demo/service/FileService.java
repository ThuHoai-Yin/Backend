package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileResponse;
import com.example.demo.model.FileUpload;

@Service
public interface FileService {
          public String getExtension(String filename);
          public String createId(String infoCode);
          public boolean checkExisted(FileUpload fileUpload);
          public List<FileResponse> getListFileByInfoCode(String infoCode);
          public List<String> deleteFile(List<String> filename,String infoCode);
          public boolean uploadFilẹ̣(MultipartFile file,String infoCode) throws IOException;
}