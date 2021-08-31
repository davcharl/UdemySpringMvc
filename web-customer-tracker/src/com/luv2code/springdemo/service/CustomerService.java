package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.ui.Model;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int theId);
	
	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

	public List<Customer> getCustomers(int theSortField);
	
}
