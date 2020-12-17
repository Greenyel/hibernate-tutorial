package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {
	
	

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		//try block is used to save student object to a DB
		try {
			
			session.beginTransaction();
			
			List<Student> students = session.createQuery("SELECT * FROM Student").list();
			
			for(Student student : students) {
				System.out.println(student);
			}
			
			
			List<Student> studentsWithNameList = session.createQuery("FROM Student s where s.firstName = 'The'").list();
			
			for(Student student : studentsWithNameList) {
				System.out.println(student);
			}
			
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
