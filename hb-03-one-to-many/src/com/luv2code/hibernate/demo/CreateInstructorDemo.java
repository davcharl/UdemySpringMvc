package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			// create the object
			Instructor tempInstructor =
					new Instructor("Susan","Sarandan","suzie@mail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com/suzie",
							"sweaters");
			
			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// NOTE: THis will also save the details object
			// because of CascaddeType.ALL
			System.out.println("Saving Instructor " + tempInstructor.toString());
			session.save(tempInstructor);
			
			// commit the instructor
			session.getTransaction().commit();
			
			// close session
			session.close();
			
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
