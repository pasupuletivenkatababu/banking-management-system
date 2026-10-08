package com.banking.service;
import com.banking.entity.Customer;
import com.banking.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService  {
	
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository= customerRepository;
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Customer not found with ID: " + id));
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		// TODO Auto-generated method stub
		
		Customer existingCustomer =getCustomerById(id);
		
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPhone(customer.getPhone());
		existingCustomer.setAddress(customer.getAddress());
		
		
		return customerRepository.save(existingCustomer) ;
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		
		Customer existingCustomer = getCustomerById(id);
		customerRepository.delete(existingCustomer);
		
	}

}
