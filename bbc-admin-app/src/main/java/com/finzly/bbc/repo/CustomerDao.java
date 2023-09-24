package com.finzly.bbc.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbc.entities.Customer;

@Repository
public class CustomerDao {
	
	@Autowired
	private SessionFactory factory;
	
	public String addNewCustomer(Customer customer) {

		Session session = factory.openSession();
		session.save(customer);
		session.beginTransaction().commit();
		return "customer added successfully!";
	}

	public Customer getCustomerById(Integer customerId) {

		Session session = factory.openSession();
		Customer customer = session.get(Customer.class, customerId);
		return customer;
	}

	public List<Customer> getAllCustomer() {

		Session session = factory.openSession();

		return session.createCriteria(Customer.class).list();

	}

//	public Customer getCustomerByEmail(String customerEmail) {
//
//		List<Customer> customers = getAllCustomer();
//		for (Customer customer : customers) {
//			if (customerEmail.equals(customer.getCustomerEmail())) {
//				return customer;
//			}
//		}
//		return null;
//	}

	public Customer getCustomerByContact(String contact) {
		List<Customer> customers = getAllCustomer();
		for (Customer customer : customers) {
			if (contact.equals(customer.getCustomerContact())) {
				return customer;
			}
		}
		return null;
	}

	public String updateCustomer(Customer customer) {

		Session session = factory.openSession();
		session.save(customer);
		session.beginTransaction().commit();
		return "customer updated successfully!";
	}

	public String deleteCustomer(Customer customerById) {

		Session session = factory.openSession();
		session.remove(customerById);
		session.beginTransaction().commit();
		return "Customer deleted successfully!";
	}

}