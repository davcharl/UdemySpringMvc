package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Use session the object to save the Java object
			Student tempStudent = new Student("Adam", "Zebra", "az@email.com");
			// Start a transaction
			session.beginTransaction();
			// Save the object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			// Commit the data
			session.getTransaction().commit();
			
			System.out.println("Saved!!");
		}
		finally {
			
		}

	}

}
