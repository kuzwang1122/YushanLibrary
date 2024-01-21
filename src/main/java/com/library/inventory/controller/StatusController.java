package com.library.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.inventory.model.entity.Inventory;
import com.library.inventory.service.StatusService;

@Controller
@RequestMapping("/user")
public class StatusController {

	@Autowired
	StatusService inventorySvc;
	
	@PostMapping("/edit")
	public String edit(@RequestParam Integer inventoryID, Model model) {
		Inventory inventory = inventorySvc.getOneInventory(inventoryID);
		inventory.setStatus(inventory.getStatus() == true?false:true);
		inventorySvc.updateInventory(inventory);
		model.addAttribute("inventoryList",inventorySvc.getAllInventory());
		return "redirect:list";
	}
}
