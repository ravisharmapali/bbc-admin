package com.finzly.bbc.services;

import java.util.List;

import com.finzly.bbc.entities.Customer;

public interface CustomerService {

	public String addNewCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(Integer customerId);
	public Customer getCustomerByEmail(String customerEmail);
	public String updateCustomer(Customer customer, int customerId);
	public String deleteCustomer(int customerId);
	
}
