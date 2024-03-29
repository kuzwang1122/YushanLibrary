package com.library.admin.model.vo;

import com.library.admin.model.entity.Admin;

import lombok.Data;

@Data
public class AdminVO {

	private Integer adminID;
	private String adminAcc;
	private String adName;
	
	public AdminVO() {
	}

	public AdminVO(Admin admin) {
		this.adminID = admin.getAdminID();
		this.adminAcc = admin.getAdminAcc();
		this.adName = admin.getAdName();
	}
	
}
