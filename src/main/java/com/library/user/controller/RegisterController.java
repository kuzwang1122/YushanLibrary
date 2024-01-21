package com.library.user.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.user.model.entity.User;
import com.library.user.service.UserService;

@Controller
@RequestMapping
public class RegisterController {

	@Autowired
	private UserService userSvc;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@PostMapping("/register")
	public String doRegister(HttpServletRequest req, Model model, HttpSession session)
			throws ServletException, IOException {

		// ===獲得請求參數===
		String name = req.getParameter("rName");
		String tel = req.getParameter("rTel").trim();
		String password = req.getParameter("rPassword");
		String confirmPassword = req.getParameter("rConfirmPassword");

		User newUser = new User();

		// ===輸入格式驗證錯誤處理===
		List<String> errMsgs = new LinkedList<String>();
		

		// name錯誤處理
		/*
		 * 使用捕獲組 () 來表示整個名字的模式。 模式包含大小寫字母、底線 [a-zA-Z_] 和漢字的 Unicode 範圍 \u4e00-\u9fa5。
		 * {2,15} 指定捕獲組內容的長度應在 2 到 15 個字符之間。
		 */
		String nameReg = "^([a-zA-Z_\u4e00-\u9fa5]{2,20})$";
		if (name == null || (name.trim().length()) == 0) {
			// 未輸入
			errMsgs.add(" 請輸入姓名！");
		} else if (!name.trim().matches(nameReg)) {
			errMsgs.add(" 姓名格式有誤：僅能輸入中、英文字母與底線，且長度必需在2到20之間。");
		}


		// tel錯誤處理
		// [0-9]+：匹配一個或多個（+）數字（0到9）
		String telReg = "^[0-9]+$";
		if (tel == null || (tel.trim().length()) == 0) {
			// 未輸入
			errMsgs.add(" 請輸入電話！");
		} else if (!tel.trim().matches(telReg)) {
			errMsgs.add(" 電話格式有誤：僅能輸入數字。");
		}

		// password錯誤處理
		if (password == null || password.trim().length() == 0) {
			errMsgs.add(" 請輸入密碼！");
		}

		// confirmPassword錯誤處理
		if (confirmPassword == null || confirmPassword.trim().length() == 0) {
			errMsgs.add(" 請再次輸入確認密碼！");
		} else if (!confirmPassword.equals(password)) {
			// 密碼與確認密碼不相同
			errMsgs.add(" 密碼與確認密碼輸入不相同，請修正！");
		}

		// ===資料放入newMem物件===
		newUser.setUserName(name);
		newUser.setPhoneNumber(tel);
		newUser.setPassword(password);

		// ===資料有誤，forward回註冊頁面===
		if (!errMsgs.isEmpty()) {
//			req.setAttribute("newUser", newUser); 
			model.addAttribute("newMem", newUser);// 含有輸入格式錯誤的mem物件,也存入req以便forward回註冊頁面時顯示
			model.addAttribute("errMsgs", errMsgs);
//			RequestDispatcher failureView = req.getRequestDispatcher("forward:/templates/login.html");
//			failureView.forward(req, res);
			return "login";
		} else {

			// ===資料正確，寄發驗證碼信件給電子信箱===
			String code = userSvc.returnAuthCode(); // 產生驗證碼
			System.out.println("Auth code is: " + code);

			// 存入Redis
			redisTemplate.opsForValue().set("MemMail:" + tel, code, Duration.ofMinutes(10));

			userSvc.sendVerificationCode(tel, name, code);

			// ===forward至驗證頁完成驗證===
//			model.addAttribute("email", email);
//			model.addAttribute("name", name);
//			model.addAttribute("newMem", newMem);
			session.setAttribute("email", tel);
			session.setAttribute("name", name);
			session.setAttribute("newUser", newUser);
//			RequestDispatcher verificationView = req.getRequestDispatcher("forward:/templates/verificationCode_page.html");
//			verificationView.forward(req, res);
			return "verificationCode_page";

		}

	}

	@PostMapping("/goole-register")
	public String googleRegister(HttpServletRequest req, Model model) {

		// ===獲得請求參數===
		String name = req.getParameter("rName");
		String tel = req.getParameter("rTel").trim();

		// 拿出google驗證時先存入session的newMem
		HttpSession session = req.getSession();
		User newUser = (User) session.getAttribute("newUser");

		// ===輸入格式驗證錯誤處理===
		List<String> errMsgs = new LinkedList<String>();

		// email唯讀不能改

		// name錯誤處理
		/*
		 * 使用捕獲組 () 來表示整個名字的模式。 模式包含大小寫字母、底線 [a-zA-Z_] 和漢字的 Unicode 範圍 \u4e00-\u9fa5。
		 * {2,15} 指定捕獲組內容的長度應在 2 到 15 個字符之間。
		 */
		String nameReg = "^([a-zA-Z_\u4e00-\u9fa5]{2,20})$";
		if (name == null || (name.trim().length()) == 0) {
			// 未輸入
			errMsgs.add(" 請輸入姓名！");
		} else if (!name.trim().matches(nameReg)) {
			errMsgs.add(" 姓名格式有誤：僅能輸入中、英文字母與底線，且長度必需在2到20之間。");
		}

		

		// tel錯誤處理
		// [0-9]+：匹配一個或多個（+）數字（0到9）
		String telReg = "^[0-9]+$";
		if (tel == null || (tel.trim().length()) == 0) {
			// 未輸入
			errMsgs.add(" 請輸入電話！");
		} else if (!tel.trim().matches(telReg)) {
			errMsgs.add(" 電話格式有誤：僅能輸入數字。");
		}

		// ===資料放入newMem物件===
		newUser.setUserName(name);
		newUser.setPhoneNumber(tel);

		// ===資料有誤，回註冊頁面===
		if (!errMsgs.isEmpty()) {
			model.addAttribute("newMem", newUser);// 含有輸入格式錯誤的mem物件也存入
			model.addAttribute("errMsgs", errMsgs);
			return "login-google";
		} else {
			// ===資料正確，存入資料庫並導向會員中心===
			userSvc.addUser(newUser);
			session.setAttribute("mem", newUser); // 存進session
			session.setAttribute("google", true);
			model.addAttribute("mem", newUser);
			model.addAttribute("google", true);
			String successMsg = "已成功完成會員註冊！";
			model.addAttribute("successMsg", successMsg);

			return "member_home_alter";
		}

	}

}
