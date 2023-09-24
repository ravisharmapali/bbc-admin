package com.finzly.bbc.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.bbc.entities.Customer;
import com.finzly.bbc.exceptions.DuplicateCustomerException;
import com.finzly.bbc.exceptions.ResourceNotFoundException;
import com.finzly.bbc.repo.CustomerDao;

@Service
public class CustomerServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	// to create a new customer
	public String addNewCustomer(Customer customer) {

		logger.info("CustomerService ::addNewCustomer()");
		// checking if already any customer is having same mail id or same contact
		// number:
		if (customer != null) {
			if (isEmailAlreadyInUse(customer.getCustomerEmail())) {
				logger.error("CustomerService :: exception while adding customer : email already exists");
				throw new DuplicateCustomerException("Customer with same email id already exists!");
			}
			if (isContactAlreadyInUse(customer.getCustomerContact())) {
				logger.error("CustomerService :: exception while adding customer : contact already exists");
				throw new DuplicateCustomerException("Customer with same contact number already exists!");
			}

			return customerDao.addNewCustomer(customer);
		}
		return null;

	}

	public List<Customer> getAllCustomer() {

		return customerDao.getAllCustomer();
	}

	public Customer getCustomerById(Integer customerId) {

		if (customerId != null) {
			return customerDao.getCustomerById(customerId);
		} else {
			logger.error("CustomerService :: exception while getting customer : not found ");
			throw new ResourceNotFoundException("no customer found with id" + customerId);
		}

	}

	public Customer getCustomerByEmail(String customerEmail) {

		if (customerEmail != null) {
			List<Customer> customers = getAllCustomer();
			for (Customer customer : customers) {
				if (customerEmail.equals(customer.getCustomerEmail())) {
					return customer;
				}
			}
			return null;

//			Customer customer = customerDao.getCustomerByEmail(customerEmail);

		}
		return null;

	}

	// method to check if any customer is already having same email id
	public boolean isEmailAlreadyInUse(String email) {

		Customer customer = getCustomerByEmail(email);
		if (customer != null)
			return true;
		else
			return false;

	}

	// method to check if any customer is already having same contact number
	public boolean isContactAlreadyInUse(String contact) {
		Customer customer = customerDao.getCustomerByContact(contact);
		if (customer != null)
			return true;
		else
			return false;

	}

	// to update customer data using customer id
	public String updateCustomer(Customer customer, int customerId) {

		Customer customerById = customerDao.getCustomerById(customerId);
		if (customerById != null) {
			customerById.setCustomerName(customer.getCustomerName());
			customerById.setCustomerEmail(customer.getCustomerEmail());
			customerById.setCustomerContact(customer.getCustomerEmail());
			customerById.setCustomerAddress(customer.getCustomerAddress());
		} else {
			logger.error("CustomerService :: customer not found : ");
			return "No customer found with id" + customerId;
		}

		return customerDao.updateCustomer(customerById);

	}

	public String deleteCustomer(int customerId) {

		Customer customerById = customerDao.getCustomerById(customerId);
		if (customerById != null) {
			String response = customerDao.deleteCustomer(customerById);
			return response;

		} else {
			logger.error("CustomerService :: customer not found : ");
			return "cant find customer with id" + customerId;
		}

	}

	public String addCustomersUsingCSV(MultipartFile file) {

//		if (file.isEmpty()) {
//			return "Please select a CSV file to upload.";
//		}
//		List<Customer> customers = new ArrayList<>();
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				String[] data = line.split(",");
//				if (data.length >= 3) {
//					Customer customer = new Customer();
//					customer.setCustomerName(data[0]);
//					customer.setCustomerEmail(data[1]);
//					customer.setCustomerContact(data[2]);
//					customer.setCustomerAddress(data[3]);
//					customers.add(customer);
//				}
//				customerDao.addNewCustomer(customer);
//			}
//		}
////		customerDao.addNewCustomer;
////		customerService.saveAll(customers); // Save customers to the database
//
//		return customers.size() + " customers were successfully uploaded.";
		return null;
	}

}