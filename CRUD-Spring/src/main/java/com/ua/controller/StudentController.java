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
import com.ua.transformer.StudentTransformer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class StudentController {

	private StudentRepository studentRepository;

	@GetMapping("/student")
	public List<Student> getStudents() {
		return studentRepository.getAll();
	}

	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentRepository.get(Integer.parseInt(id));
	}

	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.create(student);
	}

	@PutMapping("/student/{id}")
	public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		Student transformedStudent = StudentTransformer.transformStudent(id, student);
		return studentRepository.update(transformedStudent);
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		studentRepository.delete(Integer.parseInt(id));
	}
}
