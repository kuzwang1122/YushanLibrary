package com.library.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.library.user.model.entity.User;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String memLogin() {
		return "login";
	}

    @GetMapping("/login-test")
    public String memLoginTst() {
        return "login-test";
    }
	
    @GetMapping("/login-google")
    public String googleRegister(Model model, HttpSession session) {
    	User newUser = (User) session.getAttribute("newUser");
    	model.addAttribute("newUser", newUser);
    	return "login-google";
    }
    
    @GetMapping("/member/info")
	public String userInfo(Model model, HttpSession session) {
    	User user = (User) session.getAttribute("mem");
		model.addAttribute("user", user);
		if (session.getAttribute("google") != null) {
			boolean isGoogleLogin = (boolean) session.getAttribute("google");
			model.addAttribute("google", isGoogleLogin);
		}
		if (user != null) {
	        return "member_home_alter";
	    } else {
	        // 如果 mem 為 null，可能是未登入或登入訊息過期
	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
	        return "redirect:/login";
	    }
	}
}
