package com.library.borrowingrecord.model.vo;

import java.time.LocalDateTime;

import com.library.borrowingrecord.model.entity.BorrowingRecord;

import lombok.Data;

@Data
public class BorrowingRecordVO {
	
	private Integer userID;
	private Integer inventoryID;
	private LocalDateTime borrowingTime;
    private LocalDateTime returnTime;
    
    public BorrowingRecordVO() {
    	
    }
    
    public BorrowingRecordVO(BorrowingRecord borrowingrecord) {
    	this.userID = borrowingrecord.getUserID();
    	this.inventoryID = borrowingrecord.getInventoryID();
    	this.borrowingTime = borrowingrecord.getBorrowingTime();
    	this.returnTime = borrowingrecord.getReturnTime();
    	
    }
}
