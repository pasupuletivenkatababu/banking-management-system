package com.banking.controller;
import com.banking.entity.Customer;
import com.banking.service.CustomerService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	@GetMapping
	public List<Customer>getAllCustomer(){
		return customerService.getAllCustomer();
		
	}
	@GetMapping("/{id}")
	public  Customer getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}
	
	@PutMapping("/{id}")
	public Customer updateCustomer(
			@PathVariable Long id,
			@RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
		
	}
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		
		return "Customer delete successfully";
	}

}
