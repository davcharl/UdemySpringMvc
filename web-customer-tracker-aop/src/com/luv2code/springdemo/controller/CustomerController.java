package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the DAO into this controller
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from DAO
		// add customers to model
		
		List<Customer> theCustomers = null;
		
		if(sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
		} else {
			theCustomers = customerService.getCustomers();
		}
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customersx";
	}
	
	@GetMapping("/showCustomerForm")
	public String showCustomerForm(Model theModel) {
		
		// create model attribute to bind to form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer( @ModelAttribute("customer") Customer theCustomer ) {
		
		// Very basic validation hack to ensure at least email is populated
		if(!theCustomer.getEmail().isEmpty() ) {
			// save the customer using the customer service
			customerService.saveCustomer(theCustomer);
		}

		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		
		// Get the customer form our service
		Customer tempCustomer = customerService.getCustomer(theId);
		
		// Set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", tempCustomer);
		
		// send over to our form
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer( @RequestParam("customerId") int theId ) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		// Search customer using the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// Add customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customersx";
	}
	
	

}
