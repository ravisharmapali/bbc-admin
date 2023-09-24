package com.finzly.bbc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.bbc.entities.Bill;
import com.finzly.bbc.services.BillServiceImpl;

@RestController
@RequestMapping("/api/bill/")
public class BillController {

	@Autowired
	private BillServiceImpl billService;

	// add customer bill
	@PostMapping("/customer/{customerId}")
	public ResponseEntity<String> addNewBill(@RequestBody Bill bill, @PathVariable int customerId) {

		String status = billService.addNewBill(bill, customerId);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	// get all bills of customer using customer id
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Bill>> getAllBillsByCustomerId(@PathVariable int customerId) {

		List<Bill> billsByCustomerId = billService.getAllBillsByCustomerId(customerId);
		return new ResponseEntity<>(billsByCustomerId, HttpStatus.OK);
	}

	@GetMapping("{billId}")
	public ResponseEntity<Bill> getBillByid(@PathVariable int billId) {

		Bill billById = billService.getBillByid(billId);
//		Bill bill = billService.getBillByid(id).orElseThrow(() -> new ResourceNotFoundException("Bill not exits by id " + id));
//		return ResponseEntity.ok(bill);
		return new ResponseEntity<>(billById, HttpStatus.OK);
	}

	// select bill which is unpaid for all customer
	@GetMapping("/unpaid")
	public ResponseEntity<List<Bill>> getAllUnpaidBills() {

		List<Bill> allUnpaidBills = billService.getAllUnpaidBills();

		return new ResponseEntity<>(allUnpaidBills, HttpStatus.OK);

	}

	// select bill which is unpaid using customerId
	@GetMapping("/unpaid/{customerId}")
	public ResponseEntity<List<Bill>> getAllUnpaidBillsUsingCustomerId(@PathVariable int customerId) {

		List<Bill> allUnpaidBills = billService.getAllUnpaidBillsUsingCustomerId(customerId);
		return new ResponseEntity<>(allUnpaidBills, HttpStatus.OK);

	}

	// update bill
	//

	// Delete Bill By ID
	@DeleteMapping("{billId}")
	public ResponseEntity<String> deleteBill(@PathVariable int billId) {
		String deleteBill = billService.deleteBill(billId);
		return new ResponseEntity<>(deleteBill, HttpStatus.OK);
	}

}