package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.FileUpload;
@Service
public interface FileService extends JpaRepository<FileUpload, String>{
        public List<FileUpload> findByFilename(String filename);
}
