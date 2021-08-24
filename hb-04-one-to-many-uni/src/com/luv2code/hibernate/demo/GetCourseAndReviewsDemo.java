package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			
			// Get a course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			// Print the course
			System.out.println("fromDav: The Course is: " + tempCourse);
			
			// Get the reviews for this course
			System.out.println("fromDav: The Course Reviews are: " + tempCourse.getReviews() );
			
			
			
			
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
