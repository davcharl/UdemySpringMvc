package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		
		if( customerService.getCustomer(customerId) == null ) {
			throw new CustomerNotFoundException( String.format("The id entered, %d, is not a valid value", customerId) );
		}
		return customerService.getCustomer(customerId);
	}
	
	// add mapping for POST /customers - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// Just in case there is a value mistakingly here
		// we add a '0' so the saveOrUpdate DAO method knows
		// that we want to add a new customer, not update one
		theCustomer.setId(0);
		// add the customer
		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}
	
	// add mapping for the PUT method /customers - update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		// update with the customer
		customerService.saveCustomer(theCustomer);
		
		// return the customer
		return customerService.getCustomer(theCustomer.getId());
	}
	
	// add mapping for the DELETE method /customers - delete a customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomers(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		if(tempCustomer == null ) {
			throw new CustomerNotFoundException("Customer not found for id: " + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer with id: " + customerId;

	}

}
