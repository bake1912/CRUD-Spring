package com.ua.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/student")
	public List<Student> getStudents() throws SQLException {
		return studentRepository.readData();
	}

	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentRepository.readById(Integer.parseInt(id));
	}

	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.createStudent(student);
	}

	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student student) {
		return studentRepository.updateStudent(student);
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		studentRepository.deleteStudent(Integer.parseInt(id));
	}
}
