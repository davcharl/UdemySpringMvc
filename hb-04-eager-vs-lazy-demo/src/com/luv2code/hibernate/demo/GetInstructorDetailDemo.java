package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

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
			
			int theId = 2999;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstructorDetail: " +  tempInstructorDetail );
			
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor() );
			
			
			// commit the instructor
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		} catch(Exception exc) {
			System.out.println("my exception: \n");
			exc.printStackTrace();
		}
		finally {
			// close session
			System.out.println("Closing session.");
			session.close();
		}

	}

}
