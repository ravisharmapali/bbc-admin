package com.finzly.bbc.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billId;
	private int unitConsumption;
	private LocalDate billDueDate;
	private int billDuration;
	// paid or not
	private boolean isPaid;

	// amount
	private double billAmount;

//	private Date billGenerationDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	// @OneToOne
	// one to many
	@OneToMany
	private List<Payment> payments;

	public Bill() {

	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getUnitConsumption() {
		return unitConsumption;
	}

	public void setUnitConsumption(int unitConsumption) {
		this.unitConsumption = unitConsumption;
	}

	public LocalDate getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(LocalDate billDueDate) {
		this.billDueDate = billDueDate;
	}

	public int getBillDuration() {
		return billDuration;
	}

	public void setBillDuration(int billDuration) {
		this.billDuration = billDuration;
	}

	public Customer getCustomer() {
		return customer;
	}

//
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", unitConsumption=" + unitConsumption + ", billDueDate=" + billDueDate
				+ ", billDuration=" + billDuration + ", isPaid=" + isPaid + ", billAmount=" + billAmount + ", customer="
				+ customer + ", payments=" + payments + "]";
	}

}