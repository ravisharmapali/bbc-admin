package com.finzly.bbc.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private String paymentMethod; // card / wallet / cash
	private LocalDate paymemtDate;
	private double paymentAmount;
	private String paymentStatus; // success / failed/
	// discount

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Payment() {

	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDate getPaymemtDate() {
		return paymemtDate;
	}

	public void setPaymemtDate(LocalDate paymemtDate) {
		this.paymemtDate = paymemtDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMethod=" + paymentMethod + ", paymemtDate=" + paymemtDate
				+ ", paymentAmount=" + paymentAmount + ", paymentStatus=" + paymentStatus + ", bill=" + bill
				+ ", customer=" + customer + "]";
	}

}