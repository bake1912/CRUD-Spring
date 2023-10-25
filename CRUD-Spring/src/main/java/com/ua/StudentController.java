package com.ua;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@GetMapping("/students")
	public String getStudents() {
		return StudentRepository.readData().toString();
	}

	@GetMapping("/students/{id}")
	public String getStudent(@PathVariable("id") String id) {
		return StudentRepository.readById(id).toString();
	}

	@PostMapping("/students")
	public void createStudent(@RequestBody Student student) {
		StudentRepository.insertRow(student);
	}

	@PutMapping("/students")
    public void updateStudent(String id,@RequestBody Student student) {
		StudentRepository.updateRow(student);
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		Student student = new Student();
		student.setId(Integer.parseInt(id));
		StudentRepository.deleteRow(student);
	}
}
