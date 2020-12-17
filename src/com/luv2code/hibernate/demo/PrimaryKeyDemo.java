package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		//try block is used to save student object to a DB
		try {
		System.out.println("Creating new student object");
		Student tempStudent = new Student("The", "Guy", "theguyemail@gmail.com");
		Student tempNewStudent = new Student("The", "New Guy", "thenewguyemail@gmail.com");
		Student tempAppleStudent = new Student("The", "AppleGuy", "theappleguyemail@gmail.com");
		
		session.beginTransaction();
		
		System.out.println("Saving Student objects");
		session.save(tempStudent);
		session.save(tempNewStudent);
		session.save(tempAppleStudent);
		
		session.getTransaction().commit();
		System.out.println("Done!");
		}
		finally {
		factory.close();
		}

	}

}
