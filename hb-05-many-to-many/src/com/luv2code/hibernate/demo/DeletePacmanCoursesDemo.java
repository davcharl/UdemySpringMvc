package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeletePacmanCoursesDemo {

	public static void main(String[] args) {
		
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			// Get Mary's record
			int courseId = 12;
			Course tempCourse = session.get(Course.class, courseId);
			System.out.println("FromDav: The Course retrieved is: " + tempCourse);
			
			System.out.println("Deleteing the course");
			session.delete(tempCourse);
			System.out.println("Course deleted");
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
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
