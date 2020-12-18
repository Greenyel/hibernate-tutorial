package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {
	
	public static void displayStudents(List<Student> students)
	{
		for(Student student : students) {
			System.out.println(student);
		}
	}

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		//try block is used to save student object to a DB
		try {
			
			session.beginTransaction();
			
			System.out.println("\n\nAll Students: ");
			List<Student> allStudents = session.createQuery("FROM Student").list();
			displayStudents(allStudents);
			
			System.out.println("\n\nStudents with Special Name: ");
			List<Student> studentsWithNameList = session.createQuery("FROM Student s where s.firstName = 'The'"
					+ " OR s.firstName = 'Miles'").list();
			displayStudents(studentsWithNameList);
			
			System.out.println("\n\nStudents with specificEmail: ");
			List<Student> studentsWithSpecificEmail = session.createQuery("FROM Student where email LIKE '%guyemail@gmail.com'  ").list();
			displayStudents(studentsWithSpecificEmail);
			
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
