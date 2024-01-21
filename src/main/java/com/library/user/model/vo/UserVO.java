package com.library.user.model.vo;

import java.time.LocalDateTime;

import com.library.user.model.entity.User;

import lombok.Data;

@Data
public class UserVO {
	
	private Integer userID;
	private String phoneNumber;
	private String userName;
	private LocalDateTime registrationTime;
    private LocalDateTime lastLoginTime;
	
    public UserVO() {
    	
    }
    
    public UserVO( User user) {
    	this.userID = user.getUserID();
    	this.phoneNumber = user.getPhoneNumber();
    	this.userName = user.getUserName();
    	this.registrationTime = user.getRegistrationTime();
    	this.lastLoginTime = user.getLastLoginTime();
    }
}
