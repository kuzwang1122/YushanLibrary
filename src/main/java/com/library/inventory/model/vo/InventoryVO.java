package com.library.inventory.model.vo;

import java.time.LocalDateTime;

import com.library.inventory.model.entity.Inventory;
import lombok.Data;

@Data
public class InventoryVO {
	
	private Integer inventoryID;
	private String isbn;
	private Boolean status;
	private LocalDateTime storeTime;
	
	public InventoryVO() {
		
	}
	
	public InventoryVO( Inventory inventory ) {
		this.inventoryID = inventory.getInventoryID();
		this.isbn = inventory.getIsbn();
		this.status = inventory.getStatus();
		this.storeTime = inventory.getStoreTime();
	}
}
