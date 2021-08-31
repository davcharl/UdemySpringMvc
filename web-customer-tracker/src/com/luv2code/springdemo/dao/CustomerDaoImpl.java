package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.utility.SortUtils;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	// inject sessionfactory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get current session
		// create a query
		// get a list of customers from the query (by executing it)
		// return the results
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class); 
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Hibernate magic: if it finds an id in the object it will update, if not it will insert!!!
		currentSession.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// read from the database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the object from the db
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// The Generic Type <Customer> can be omitted; at runtime, a Type of Object is assumed
		Query<Customer> theQuery = null;

		// Only search when three letters are entered
		if(theSearchName != null && theSearchName.trim().length() > 2) {
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like:theName OR lower(lastName) like:theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// If no name matched, return all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		// execute the query
		List<Customer> customers = theQuery.getResultList();
		return customers;

	}

	@Override
	public List<Customer> getCustomers(int theSortField) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		List<Customer> customers = null;
		String theFieldName = null;
		
		switch(theSortField) {
			case SortUtils.FIRST_NAME:
				theFieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
		}
		
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> sortCustomers = currentSession.createQuery(queryString, Customer.class);
		
		// Execute the query
		customers = sortCustomers.getResultList();
		
		return customers;
	}
	

	

}
