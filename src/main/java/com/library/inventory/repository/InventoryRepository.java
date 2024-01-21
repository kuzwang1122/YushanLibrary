package com.library.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.inventory.model.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	Inventory findByIsbn(String isbn);
}
