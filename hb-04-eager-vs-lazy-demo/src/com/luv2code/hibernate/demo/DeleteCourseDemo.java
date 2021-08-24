package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			// Retrieve the course that we want to delete
			int theId = 1;
			Course tempCourse = session.get(Course.class, theId);
			System.out.println("The course to delete: " + tempCourse);
			
			// Delete the course reference from the instructor
			session.delete(tempCourse);
			
			// Delete the course from table
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Course deleted!");
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// close session
			session.close();
			// close factory
			factory.close();
		}

	}

}
