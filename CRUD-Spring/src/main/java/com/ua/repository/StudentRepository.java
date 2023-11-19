package com.ua.repository;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ua.entity.Student;
import com.ua.jooq.Tables;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.jooq.Record;

@AllArgsConstructor
@Repository
public class StudentRepository {

	private DSLContext dsl;

	public List<Student> getAll() {
		List<Student> students = dsl.select().from(Tables.STUDENT).fetchInto(Student.class);
		return students;
	}

	public Student get(Integer id) {
		Student student = dsl.select().from(Tables.STUDENT).where(Tables.STUDENT.ID.eq(id)).fetchOneInto(Student.class);
		return student;
	}

	public Student create(Student student) {
		Student returnedStudent = dsl.insertInto(Tables.STUDENT).set(Tables.STUDENT.FIRSTNAME, student.getFirstName())
				.set(Tables.STUDENT.LASTNAME, student.getLastName())
				.set(Tables.STUDENT.MIDDLENAME, student.getMiddleName()).returning().fetchOneInto(Student.class);
		return returnedStudent;
	}

	public void delete(Integer id) {
		dsl.delete(Tables.STUDENT).where(Tables.STUDENT.ID.eq(id)).execute();
	}

	public Student update(Student student) {
		Student returnedStudent = dsl.update(Tables.STUDENT).set(Tables.STUDENT.FIRSTNAME, student.getFirstName())
				.set(Tables.STUDENT.LASTNAME, student.getLastName())
				.set(Tables.STUDENT.MIDDLENAME, student.getMiddleName()).where(Tables.STUDENT.ID.eq(student.getId()))
				.returning().fetchOneInto(Student.class);
		return returnedStudent;
	}
}
