package com.studentmanagment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagment.entity.student;

@Service	
public interface StudentService {
		public List<student> findAll();
		
		public student findById (int theId);
		 public void save (student thestudent) ;
		 public void deleteById (int theId);
		
	}


