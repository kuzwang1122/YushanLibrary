package com.library.user.model.dto;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginDTO implements Serializable{

	@NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "09\\d{8}", message = "Invalid phone number format")
    private String phoneNumber;
	
	@NotEmpty(message = "The password is not empty")
    @NotBlank(message = "The password is not blank")
    private String password;
	
	public UserLoginDTO() {
		
	}
}
