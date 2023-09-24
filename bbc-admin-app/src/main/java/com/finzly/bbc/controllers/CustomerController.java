package com.finzly.bbc.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.bbc.entities.Customer;
import com.finzly.bbc.services.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	// to add a new customer
	@PostMapping("/")
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {

		String createdCustomer = customerService.addNewCustomer(customer);
		return new ResponseEntity(createdCustomer, HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public String addCustomersUsingCSV(@RequestParam("file") MultipartFile file) throws IOException {

		return customerService.addCustomersUsingCSV(file);
		
	}

	// to get customer record using customer id
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@RequestParam Integer customerId) {

		Customer foundCustomer = customerService.getCustomerById(customerId);
		if (foundCustomer != null) {
			return new ResponseEntity(foundCustomer, HttpStatus.FOUND);
		} else {
			return new ResponseEntity(foundCustomer, HttpStatus.NOT_FOUND);

		}

	}

	// to get customer record using customer email
	@GetMapping("/{customerEmail}")
	public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String customerEmail) {

		Customer foundCustomer = customerService.getCustomerByEmail(customerEmail);
		return new ResponseEntity(foundCustomer, HttpStatus.FOUND);
	}

	// to get all of the customers
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> allCustomer = customerService.getAllCustomer();
		return new ResponseEntity(allCustomer, HttpStatus.OK);
	}

	// to update a customer data using customer id
	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestParam int customerId) {
		customerService.updateCustomer(customer, customerId);
		return null;
	}

	@DeleteMapping("{customerId}")
	public ResponseEntity<HttpStatus> deleteCutomer(@PathVariable int customerId) {
		customerService.deleteCustomer(customerId);
		return null;
	}

}