package com.finzly.bbc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.bbc.entities.Bill;
import com.finzly.bbc.exceptions.ResourceNotFoundException;
import com.finzly.bbc.repo.BillDao;
import com.finzly.bbc.util.BillUtil;

@Service
public class BillServiceImpl implements BillService {

	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

	@Autowired
	private BillDao billDao;

	public String addNewBill(Bill bill, int customerId) {
		logger.info("BillService :: addNewBill()");
		if (bill != null) {
			// setting bill amount
			bill.setBillAmount(BillUtil.getRatePerUnit() * bill.getUnitConsumption());
			return billDao.addNewBill(bill, customerId);
		}
		return null;
	}

	public List<Bill> getAllBillsByCustomerId(int customerId) {
		logger.info("BillService :: getAllBillsByCustomerId()");
		List<Bill> billsByCustomerId = billDao.getAllBillsByCustomerId(customerId);
		return billsByCustomerId;
	}

	public Bill getBillByid(int billId) {
		logger.info("BillService :: getBillByid()");

		Bill billById = billDao.getBillByid(billId);
		if (billById != null) {
			return billById;
		} else {
			logger.error("BillService :: Exception no bill found ");
			throw new ResourceNotFoundException("No bill found with bill id " + billId);
		}

	}

	// all unpaid bills:
	public List<Bill> getAllUnpaidBills() {
		logger.info("BillService :: getAllUnpaidBills()");

		List<Bill> allUnpaidBills = billDao.getAllUnpaidBills();
		if (allUnpaidBills != null) {
			return allUnpaidBills;
		} else {
			logger.error("BillService :: Exception no bill found ");
			throw new ResourceNotFoundException("No unpaid bills found");
		}

	}

	public String deleteBill(int billId) {
		logger.info("BillService :: deleteBill()");
		Bill billByid = billDao.getBillByid(billId);
		if (billByid != null) {
			return billDao.deleteBill(billId);
		} else {
			logger.error("BillService :: Exception no bill found ");
			throw new ResourceNotFoundException("No bill found with id " + billId);
		}

	}

	public List<Bill> getAllUnpaidBillsUsingCustomerId(int customerId) {
		logger.info("BillService :: getAllUnpaidBillsUsingCustomerId()");
		List<Bill> allUnpaidBills = billDao.getAllUnpaidBillsUsingCustomerId(customerId);
		if (allUnpaidBills != null) {
			return allUnpaidBills;
		} else {
			logger.error("BillService :: Exception no bill found ");
			throw new ResourceNotFoundException("No unpaid bills found for customer with id " + customerId);
		}
	}

}
