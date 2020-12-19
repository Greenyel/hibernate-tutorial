package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

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
			
			//update retrieved student
			tempStudent.setEmail("newemail@gmail.com");
			
			//commit transaction will update row in a DataBase
			//no explicit .update() is required
			session.getTransaction().commit();
			
			
			
			//Update all rows:
			//note that after transaction commit, you have to:
			//1.)session = factory.getCurrentSession();
			//otherwise, without first step, you will get an error:
			// Exception in thread "main" java.lang.IllegalStateException: Session/EntityManager is closed
			//session = factory.getCurrentSession();
			//2.)session.beginTransaction();
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("UPDATE Student SET lastName = 'Guuuuuuy'").executeUpdate();
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
