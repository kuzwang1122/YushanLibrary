package com.library.inventory.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.inventory.model.entity.Inventory;
import com.library.inventory.repository.InventoryRepository;

@Service
public class StatusService {

	@Autowired
	private InventoryRepository inventoryR;
	
	public StatusService() {
		super();
	}
	
	public Inventory getOneInventory(Integer inventoryID) {
		return inventoryR.getReferenceById(inventoryID);
	}
	
	public Inventory updateInventory(Integer inventoryID,String isbn,Boolean status,LocalDateTime storeTime) {

		Inventory inventory = new Inventory();

		inventory.setInventoryID(inventoryID);
		inventory.setIsbn(isbn);
		inventory.setStatus(status);
		inventory.setStoreTime(storeTime);
		
		inventoryR.save(inventory);

		return inventory;
	}

	public Inventory updateInventory(Inventory inventory) {
		inventoryR.save(inventory);
		return inventory;
	}
	
	public java.util.List<Inventory> getAllInventory() {
		return inventoryR.findAll();
	}
}
