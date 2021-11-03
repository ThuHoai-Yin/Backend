package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.FileUpload;
@Service
public interface FileServiceCustom {
          public String getExtension(String filename);
          public String createId(String infoCode);
          public boolean checkExisted(FileUpload fileUpload);
}
