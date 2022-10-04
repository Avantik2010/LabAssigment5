package com.studentmanagment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentmanagment.entity.student;

import com.studentmanagment.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@org.springframework.beans.factory.annotation.Autowired
	private StudentService studentService;
	
	@RequestMapping ("/list")
	public String listStudents(Model theModel) {
		System.out.println("request recieved");
		List <student> theStudents = studentService.findAll();
		theModel.addAttribute ("Student",theStudents);
		return "list-students";
		
	}
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		student student1 = new student();
		student1 = studentService.findById(id);
		System.out.println("Rcord for Updte "+student1);
		model.addAttribute("Student",student1);

		return "student-form";

	}
	@RequestMapping ("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		student theStudent =new student();
		
		theModel.addAttribute("Student",theStudent);
		return "student-form";
	}
	@PostMapping ("/save")
	public String saveBook (@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		
		System.out.println(id);
		student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else
			theStudent = new student(firstName, lastName, course, country);
		studentService.save(theStudent);
		
		return"redirect:/students/list";
		
	}
	
	@RequestMapping("/delete")
	
	public String delete (@RequestParam("studentId") int theId) {
		
		studentService.deleteById(theId);
		
		
		return"redirect:/students/list";
			
	}

}
