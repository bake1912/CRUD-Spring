package com.ua.repository;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ua.entity.Student;
import com.ua.jooq.Tables;
import java.util.ArrayList;
import java.util.List;
import org.jooq.Record;

@Repository
public class StudentRepository {

	@Autowired
	private DSLContext dsl;

	public List<Student> readData() {
		List<Student> students = new ArrayList<>();
		Result<Record> result = dsl.select().from(Tables.STUDENT).fetch();
		for (Record r : result) {
			students.add(getStudentEntity(r));
		}
		return students;
	}

	public Student readById(Integer id) {
		Record record = dsl.select().from(Tables.STUDENT).where(Tables.STUDENT.ID.eq(id)).fetchOne();
		Student student = getStudentEntity(record);
		return student;
	}

	public Student createStudent(Student student) {
		Record record = dsl.insertInto(Tables.STUDENT).set(Tables.STUDENT.FIRSTNAME, student.getFirstName())
				.set(Tables.STUDENT.LASTNAME, student.getLastName())
				.set(Tables.STUDENT.MIDDLENAME, student.getMiddleName()).returning().fetchOne();
		Student returnedStudent = getStudentEntity(record);
		return returnedStudent;
	}

	public void deleteStudent(Integer id) {
		dsl.delete(Tables.STUDENT).where(Tables.STUDENT.ID.eq(id)).execute();
	}

	public Student updateStudent(Student student) {
		Record record = dsl.update(Tables.STUDENT).set(Tables.STUDENT.FIRSTNAME, student.getFirstName())
				.set(Tables.STUDENT.LASTNAME, student.getLastName())
				.set(Tables.STUDENT.MIDDLENAME, student.getMiddleName()).where(Tables.STUDENT.ID.eq(student.getId()))
				.returning().fetchOne();
		Student returnedStudent = getStudentEntity(record);
		return returnedStudent;
	}

	private Student getStudentEntity(Record r) {
		Integer id = r.getValue(Tables.STUDENT.ID, Integer.class);
		String firstName = r.getValue(Tables.STUDENT.FIRSTNAME, String.class);
		String lastName = r.getValue(Tables.STUDENT.LASTNAME, String.class);
		String middleName = r.getValue(Tables.STUDENT.MIDDLENAME, String.class);
		Student student = new Student(id, firstName, lastName, middleName);
		return student;
	}
}
