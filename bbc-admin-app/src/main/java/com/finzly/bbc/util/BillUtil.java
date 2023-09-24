package com.finzly.bbc.util;

import java.time.LocalDate;

import com.finzly.bbc.entities.Bill;

public class BillUtil {

	private static final double RATE_PER_UNIT = 41.50; // INR per kWh
	private static final double EARLY_PAYMENT_DISCOUNT = 0.05; // 5% discount
	private static final double ONLINE_PAYMENT_DISCOUNT = 0.05; // 5% discount

	public static double getRatePerUnit() {
		return RATE_PER_UNIT;
	}

	public static double getBillAmount(double unitsConsumed, LocalDate dueDate, boolean isOnlinePayment) {

		// calculate the total amount based on units consumed and rate per unit
		double totalAmount = unitsConsumed * RATE_PER_UNIT;

		// apply online payment discount if applicable
		if (isOnlinePayment) {
			totalAmount = totalAmount - (totalAmount * ONLINE_PAYMENT_DISCOUNT);
		}

		// calculate the early payment discount deadline
		LocalDate earlyPaymentDeadline = dueDate.minusDays(2);

		// Check if the current date is before the early payment deadline
		if (LocalDate.now().isBefore(earlyPaymentDeadline)) {
			// Apply early payment discount if paid before the deadline
			totalAmount -= totalAmount * EARLY_PAYMENT_DISCOUNT;
		}
//
//		// Create a Bill object
//		Bill bill = new Bill();
//		bill.setBillUnits(unitsConsumed);
//		bill.setDueDate(dueDate);
//		bill.setTotalAmount(totalAmount);

		return totalAmount;
	}
}
