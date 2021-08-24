package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			// Get the Instructor from the DB
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("fromDav: Instructor: " + tempInstructor);
			
			System.out.println("fromDav: Courses: " + tempInstructor.getCourses() );
			
			// commit the transaction
			session.getTransaction().commit();
			
			// I want to break the app by closing the session
			// and then continue the lazy fetch
			session.close();
			System.out.println("The session is now closed");
			
			// get the courses for this instructor
			System.out.println("fromDav: Courses: " + tempInstructor.getCourses() );
			
			System.out.println("fromDav: Done!");
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// close session
			//session.close();
			// close factory
			factory.close();
		}

	}

}
