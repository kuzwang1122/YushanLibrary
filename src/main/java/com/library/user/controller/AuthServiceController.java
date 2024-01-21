package com.library.user.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.exception.UnauthorizedException;
import com.library.user.model.dto.UserLoginDTO;
import com.library.user.model.entity.User;
import com.library.user.repository.UserRepository;

@Service
public class AuthServiceController {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByPhoneNumber (String phoneNumber) {
		return this.userRepository.findByPhoneNumber(phoneNumber);
	}

	public User verify(UserLoginDTO dto, HttpServletRequest req) throws UnauthorizedException {
		User user = this.findByPhoneNumber(dto.getPhoneNumber());
        // 判斷是不是沒有撈到成員，或者是密碼不同，往外拋 401 的 exception
        if (user == null || !dto.getPassword().equals(user.getPassword())){
            throw new UnauthorizedException("The account or password is incorrect");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return user;
    }
	
	public Object validate(HttpServletRequest req, String attrName) throws UnauthorizedException {
        Object entity =  req.getSession().getAttribute(attrName);
        if(Objects.isNull(entity)) throw new UnauthorizedException("Sorry, you are not authorized to access this resource. Please provide valid credentials.");
        return entity;
    }
}
