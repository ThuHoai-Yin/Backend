package com.example.demo.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.InfoFile;

@Repository
public interface InforFileService extends JpaRepository<InfoFile, String>{

}
