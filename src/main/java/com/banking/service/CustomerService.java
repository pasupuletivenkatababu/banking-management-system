package com.banking.service;

import com.banking.entity.Customer;
import java.util.List;
public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	
	List<Customer> getAllCustomer();
	
	Customer getCustomerById(Long id);
	
	Customer updateCustomer(Long id ,Customer customer);
	
	void deleteCustomer(Long id);

}
