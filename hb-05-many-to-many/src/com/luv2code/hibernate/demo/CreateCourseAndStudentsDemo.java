package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			int theId = 2;
			Student tempStudent = session.get(Student.class, theId);
			System.out.println("FromDav: The student retrieved is: " + tempStudent);
			
			// Add Mary to courses
			Course tempCoursePac = session.get(Course.class, 10);
			Course tempCourseFriz = session.get(Course.class, 11);
			
			System.out.println("FromDav: adding student to courses");
			tempCoursePac.addStudent(tempStudent);
			tempCourseFriz.addStudent(tempStudent);
			System.out.println("FromDav: Adding complete!!");
			System.out.println("FromDav: Saving student to courses");
			session.save(tempCoursePac);
			session.save(tempCourseFriz);
			System.out.println("FromDav: Saving complete!!");
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
