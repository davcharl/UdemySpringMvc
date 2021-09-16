package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		try {
			// add a boolean flag to simulate an exception
			boolean tripwire = true;
			// call method to find account
			theAccounts = theAccountDAO.findAccounts(tripwire);
		} catch (Exception exc) {
			System.out.println("\n\nMain Program ... caught exception:\n" + exc);
		}
		
		// display the accounts
		System.out.println("\n\nMain PRogram: AfterFinallyDempApp\n");
		System.out.println(theAccounts);
		
		
		// close the context
		context.close();
	}

}










