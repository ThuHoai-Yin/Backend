package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.InfoFile;
@Repository
public interface InfoFileRepository extends JpaRepository<InfoFile, String>{

}

 