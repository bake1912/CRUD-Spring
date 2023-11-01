package com.ua.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.ua.config.DatabaseConfig;
import com.ua.entity.Student;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class StudentRepository {
	
	private static SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	public List<Student> readData() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM Student", Student.class).list();
		}
	}

	public Student readById(String id) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Student.class, id);
		}
	}

	public Student insertRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
			log.info("Student inserted successfully.");
			return student;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public Student updateRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
			log.info("Student updated successfully.");
			return student;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public void deleteRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
			log.info("Student deleted successfully.");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
