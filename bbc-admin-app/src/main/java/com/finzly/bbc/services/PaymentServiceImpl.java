package com.finzly.bbc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.bbc.entities.Bill;
import com.finzly.bbc.repo.PaymentDao;
import com.finzly.bbc.util.Invoice;

@Service
public class PaymentServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentDao paymentDao;

	public void createNewCashPayment(int billId) {
		logger.info("PaymentService :: createNewCashPayment()");
		Bill billByBillId = paymentDao.getBillByBillId(billId);
		int customerId = billByBillId.getCustomer().getCustomerId();
		paymentDao.createNewCashPayment(billId);
	}

	public void getAllTransactionsByCustomerId(int customerId) {
		logger.info("PaymentService :: getAllTransactionsByCustomerId()");
		paymentDao.getAllTransactionsByCustomerId(customerId);

	}

	public Invoice getPaymentBillPrinted(int billId) {
		logger.info("PaymentService :: getPaymentBillPrinted()");
		
		return null;
	}

}
