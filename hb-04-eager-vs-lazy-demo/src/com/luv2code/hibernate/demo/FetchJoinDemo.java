package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			// Get the Instructor from the DB with HQL
			int theId = 1;

			Query<Instructor> query = 
					session.createQuery( "select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id=:theInstructorId",
					Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute the query and get result
			// data is now in a memory object so we can close the session after this next statement,
			// if we want but still be able to access the data
			Instructor tempInstructor = query.getSingleResult(); 
			
			System.out.println("fromDav: Instructor: " + tempInstructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			session.close();
			System.out.println("\nThe session is now closed\n");
			System.out.println("fromDav: Courses: " + tempInstructor.getCourses());
			
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
