package com.finzly.bbc.services;

public interface PaymentService {

	public void createNewPayment();

	public void getAllTransactionsByCustomerId(int customerId);
}
