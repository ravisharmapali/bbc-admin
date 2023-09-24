package com.finzly.bbc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finzly.bbc.services.PaymentServiceImpl;
import com.finzly.bbc.util.Invoice;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentService;

	// paid in cash mode
	@PostMapping("/cash/{billId}")
	public ResponseEntity<?> createNewCashPayment(@PathVariable int billId) {
		paymentService.createNewCashPayment(billId);
		return null;
	}

	// to fetch the txn of a customer
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getAllTransactionsByCustomerId(@PathVariable int customerId) {

		paymentService.getAllTransactionsByCustomerId(customerId);
		return null;
	}

	@GetMapping("print/{billId}")
	public ResponseEntity<Invoice> getPaymentBillPrinted(@PathVariable int billId) {
		paymentService.getPaymentBillPrinted(billId);
		return null;
	}
	// get all customer payments
	// get specific customer payment
	// get all bills by custID ,

	// do bill payment

}