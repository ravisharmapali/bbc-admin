package com.finzly.bbc.services;

import java.util.List;

import com.finzly.bbc.entities.Bill;

public interface BillService {

	public List<Bill> getAllBillsByCustomerId(int customerId);

	public String addNewBill(Bill bill, int customerId);

	public Bill getBillByid(int billId);
}
