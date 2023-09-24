package com.finzly.bbc.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbc.entities.Bill;
import com.finzly.bbc.entities.Customer;
import com.finzly.bbc.util.BillUtil;

@Repository
public class BillDao {

	@Autowired
	private SessionFactory factory;

	@SuppressWarnings("deprecation")
	public List<Bill> getAllBillsByCustomerId(int customerId) {

		Session session = factory.openSession();
		Criteria billCriteria = session.createCriteria(Bill.class);

		// fetch customer with the id:
		Criteria custCriteria = session.createCriteria(Customer.class);
		Customer customer = (Customer) custCriteria.add(Restrictions.eq("customerId", customerId)).setMaxResults(1)
				.uniqueResult();

		billCriteria.add(Restrictions.eq("customer", customer));
		List billList = billCriteria.list();
		return billList;
	}

	public String addNewBill(Bill bill, int customerId) {
		Session session = factory.openSession();
		Customer customer = session.get(Customer.class, customerId);
		if (customer != null) {
			bill.setCustomer(customer);// setting customer
//			bill.setPaid(bill.isPaid());
			session.save(bill);
			session.beginTransaction().commit();
			return "Bill added successfully!";
		} else {
			return "customer not found with id" + customerId;
		}

	}

	@SuppressWarnings("deprecation")
	public Bill getBillByid(int billId) {
		Session session = factory.openSession();
		Bill bill = session.get(Bill.class, billId);
		return bill;
	}

	@SuppressWarnings("deprecation")
	public List<Bill> getAllUnpaidBills() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Bill.class);
		List<Bill> listOfUnpaidBills = criteria.add(Restrictions.eq("isPaid", false)).list();

		return listOfUnpaidBills;

	}

	@SuppressWarnings("deprecation")
	public List<Bill> getAllUnpaidBillsUsingCustomerId(int customerId) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Bill.class);
		List list = null;
		Customer customer = session.get(Customer.class, customerId);
		if (customer != null) {
			Criteria custCriteria = session.createCriteria(Customer.class);

			list = custCriteria.add(Restrictions.eq("customerId", customerId)).list();
		}
//		Customer cust = (Customer)list.get(0);
//		int id = cust.getCustomerId();
//		criteria.add(Restrictions.eq("isPaid", false));

		return criteria.list();
	}

	// service to delete bill using bill id :
	public String deleteBill(int billId) {

		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Bill.class);
		Bill bill = (Bill) criteria.add(Restrictions.eq("billId", billId)).uniqueResult();
		session.remove(bill);
		session.beginTransaction().commit();
		session.close();
		return "Bill deleted successfully!";
	}

}
