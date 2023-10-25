package com.ua;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepository {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public static List<Student> readData() {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Student";
			Query<Student> query = session.createQuery(hql, Student.class);
			return query.getResultList();
		}
	}

	public static Student readById(String id) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Student WHERE id=" + id;
			Query<Student> query = session.createQuery(hql, Student.class);
			return query.getSingleResult();
		} 
	}

	public static void insertRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
			System.out.println("Student inserted successfully.");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void updateRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
			System.out.println("Student updated successfully.");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void deleteRow(Student student) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
			System.out.println("Student deleted successfully.");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
