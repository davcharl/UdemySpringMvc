package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

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
			
			
			// create a course
			Course tempCourse = new Course("Perspecasity - Flipping the script");

			// save the course and leverage the cascade all
			System.out.println("FromDav: Saving the course");
			System.out.println("FromDav: " + tempCourse);
			session.save(tempCourse);
			

			// Create students
			Student tempStudent1 = new Student("John", "Doe", "jd@mail.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@mail.com");
			
			// Add students to the course
			System.out.println("FromDav: Creating Students");
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			System.out.println("FromDav: Students created");
			
			// Save the students
			System.out.println("\nFromDav: Saving Students....");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nFromDav: Students saved!\n" + tempCourse.getStudents() );
			
			
			
			
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
