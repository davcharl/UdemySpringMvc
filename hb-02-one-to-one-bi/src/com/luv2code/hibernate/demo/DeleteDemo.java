package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			// get an instructor by primary key
			int theId = 1;
			Instructor tempinstructor = session.get(Instructor.class, theId);
			// check result for null
			if(tempinstructor != null) {
				// delete instructor
				// This will also delete the associated InstructorDetail record
				System.out.println("Deleting instructor");
				session.delete(tempinstructor);
			} else {
				System.out.println("Instructor not found for deletion!");
			}
			
			// commit the instructor
			session.getTransaction().commit();
			
			// close session
			session.close();
			
			System.out.println("Done!");
		}
		finally {
			
		}

	}

}
