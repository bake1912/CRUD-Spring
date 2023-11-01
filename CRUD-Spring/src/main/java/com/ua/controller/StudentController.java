package com.ua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ua.entity.Student;
import com.ua.repository.StudentRepository;

@RestController
public class StudentController {
	
	private StudentRepository studentRepository = new StudentRepository();

	@GetMapping("/student")
	public List<Student> getStudents() {
		return studentRepository.readData();
	}

	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentRepository.readById(id);
	}

	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.insertRow(student);
	}

	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student student) {
		return studentRepository.updateRow(student);
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		studentRepository.deleteRow(new Student(Integer.parseInt(id)));
	}
}
