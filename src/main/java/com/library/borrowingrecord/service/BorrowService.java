package com.library.borrowingrecord.service;


import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.borrowingrecord.model.entity.BorrowingRecord;
import com.library.borrowingrecord.repository.BorrowingRecordRepository;

@Service
public class BorrowService {
   
	@Autowired
	private BorrowingRecordRepository borrowR;
	
	public BorrowService() {
		super();
	}
	
	public BorrowingRecord getOneIBorrowingRecord(Integer userID) {
		return borrowR.getReferenceById(userID);
	}
	
	public BorrowingRecord updateBorrowingRecord(Integer userID,Integer inventoryID,LocalDateTime borrowingTime,LocalDateTime returnTime) {

		BorrowingRecord borrowingRecord = new BorrowingRecord();

		borrowingRecord.setUserID(userID);
		borrowingRecord.setInventoryID(inventoryID);
		borrowingRecord.setBorrowingTime(borrowingTime);
		borrowingRecord.setReturnTime(returnTime);
		
		borrowR.save(borrowingRecord);

		return borrowingRecord;
	}

	public BorrowingRecord updateBorrowingRecord(BorrowingRecord borrow) {
		borrowR.save(borrow);
		return borrow;
	}
	
	public java.util.List<BorrowingRecord> getAllBorrowingRecord() {
		return borrowR.findAll();
	}
}
