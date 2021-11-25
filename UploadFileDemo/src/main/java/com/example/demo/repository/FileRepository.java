package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FileUpload;
@Repository
public interface FileRepository extends JpaRepository<FileUpload, String>{
    public List<FileUpload> findByFilename(String filename);
    public List<FileUpload> findByCode_info_file(String code_info_file);
}
