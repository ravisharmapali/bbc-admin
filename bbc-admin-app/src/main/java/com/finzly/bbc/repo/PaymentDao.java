package com.finzly.bbc.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.bbc.entities.Bill;
import com.finzly.bbc.entities.Payment;

@Repository
public class PaymentDao {

	@Autowired
	private SessionFactory factory;

	public List<Payment> getAllTransactionsByCustomerId(int customerId) {

		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("customerId", customerId));
		return criteria.list();
	}

	public void createNewCashPayment(int billId) {
		Session session = factory.openSession();
		Bill bill = session.get(Bill.class, billId);

		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("billId", billId));
		// checking if bill is already paid
		if (bill.getIsPaid()) {
//			throw new BillPaymentException("Bill already paid with bill id " + billId);
		
		}
		
		session.close();
	}

	public Bill getBillByBillId(int billId) {
		Session session = factory.openSession();
		Bill bill = session.get(Bill.class, billId);

		return bill;
	}
}