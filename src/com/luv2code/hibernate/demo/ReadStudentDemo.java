package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class ReadStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
				//Save tempStudent object to a DataBase
			Student tempStudent = new Student("The", "Guy", "theguyemail@gmail.com");
			
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
			

			System.out.println("Saved Student. Generated id: " + tempStudent.getId());
			
				//retrieve saved object from Database based on Id
			//get a new session and start transaction 
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting student with ID:" + tempStudent.getId());
			Student retrievedStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Retrieved Student object:" + retrievedStudent);
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
