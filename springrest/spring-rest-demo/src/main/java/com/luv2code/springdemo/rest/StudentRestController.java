package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents = new ArrayList<>();
	
	// define @PostConstruct to load the student data.... only once
	@PostConstruct
	public void loadData() {
		theStudents.add(new Student("James","Hickey"));
		theStudents.add(new Student("Alanna","Kuznetsova"));
		theStudents.add(new Student("Llana","Carlos"));
	}
	
	// define endpoint for "/students" to return a list of all our students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	// define endpoint for "/{students_id}" to return a specific student
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// logic to check stuentId in list size
		if(studentId >= theStudents.size() || studentId < 0 ) {
			throw new StudentNotFoundException(
					String.format("The student id that you entered, %d, was not found", studentId));
		}
	
		return theStudents.get(studentId);		
	}

}