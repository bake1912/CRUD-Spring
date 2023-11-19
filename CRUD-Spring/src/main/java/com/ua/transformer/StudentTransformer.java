package com.ua.transformer;

import com.ua.entity.Student;

public class StudentTransformer {
	
	public Student transformStudent(Integer id, Student student) {
		student.setId(id);
		return student;
	}

}
