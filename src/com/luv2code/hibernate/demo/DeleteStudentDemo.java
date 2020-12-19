package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			//Update only one row:
			int studentId = 1;
			session.beginTransaction();
			
			//retrieve student from DB
			Student tempStudent = session.get(Student.class, studentId);
			session.delete(tempStudent);
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("DELETE FROM Student WHERE id=2").executeUpdate();
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
