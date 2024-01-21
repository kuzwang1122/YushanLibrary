package com.library.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.user.model.entity.User;
import com.library.user.repository.UserRepository;

@WebServlet("/memloginhandler")
public class UserLoginHandler extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository userR;
	
//	public void setRepository(MemRepository repository) {
//        this.repository = repository;
//    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
								 throws ServletException, IOException {
		
		
		res.setContentType("text/html; charset=UTF-8");
		
		String account = req.getParameter("userAccount");
		String password = req.getParameter("userPassword");
		User user = userR.findByPhoneNumber(account);
	
		// 需 1.mem物件不為空值 2.密碼正確
		if (user != null && password.equals(user.getPassword())) {
			// 登入成功
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) { // 檢查有無來源網頁，有就重導回去
					res.sendRedirect(location);
					return; // 程式中斷
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			res.sendRedirect(req.getContextPath() + "/member_home_alter.html"); // 如無來源網頁則導至會員中心
		} else {
			// 登入失敗
			req.setAttribute("loginFailed", true);
			req.getRequestDispatcher("login.html").forward(req, res);
		}
		
	}
}
