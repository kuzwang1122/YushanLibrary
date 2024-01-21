package com.library.user.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.library.user.model.entity.User;
import com.library.user.repository.UserRepository;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userR;
	
	public UserService() {
		super();
	}

	public User findByUserName(String userName) {
		return userR.findByUserName(userName);
	}
	
	public User findByPhoneNumber(String phoneNumber) {
		return userR.findByPhoneNumber(phoneNumber);
	}
	
	public User addUser(String phoneNumber, String password, String userName) {

		User user = new User();

		user.setPhoneNumber(phoneNumber);
		user.setPassword(password);
		user.setUserName(userName);
	
		userR.save(user);

		return user;
	}
	
	public User addUser(User user) {
		userR.save(user);
		return user;
	}
	
	public User updateUser(Integer userID,String phoneNumber,String passWord,String userName) {
		
		User user = new User();
		
		user.setUserID(userID);
		user.setPhoneNumber(phoneNumber);
		user.setPassword(passWord);
		user.setUserName(userName);
		
		userR.save(user);
		
		return user;
	}
	
	public User updateuser(User user) {
		userR.save(user);
		return user;
	}

	public User getOneuser(Integer userID) {
		return userR.getReferenceById(userID);
	}

	public java.util.List<User> getAllUser() {
		return userR.findAll();
	}

	// 寄送驗證碼
	// 設定傳送郵件：至收信人的Email信箱、Email主旨、收件人稱呼
	public void sendVerificationCode(String to, String ch_name, String code) {

		String subject = "玉山圖書館驗證碼通知";
		String messageText = ch_name + "先生/小姐，歡迎啟用玉山圖書館會員帳號！" + " 您的驗證碼為：" + code + "\n"
				+ " （該驗證碼將在10分鐘後失效，請盡快在驗證頁完成驗證。）";

		MailService mailService = new MailService();
		mailService.sendMail(to, subject, messageText);

	}

	// 產生隨機8位驗證碼，包含英文大小寫與亂數
	public String returnAuthCode() {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int condition = (int) (Math.random() * 3) + 1; // 1, 2, 3
			switch (condition) {
			case 1:
				char c1 = (char) ((int) (Math.random() * 26) + 65); // ASCII隨機英文大寫
				sb.append(c1);
				break; // 跳脫block
			case 2:
				char c2 = (char) ((int) (Math.random() * 26) + 97); // 隨機英文小寫
				sb.append(c2);
				break; // 跳脫block
			case 3:
				char c3 = (char) ((int) (Math.random() * 10) + 48); // 0~9隨機亂數
				sb.append(c3);
			}
		}

		return sb.toString();
	}

	// Google sign-in verification
	public GoogleIdToken googleVerify(String idTokenString) throws GeneralSecurityException, IOException {
		final String CLIENT_ID = "831533266849-tvfvcmpicrsvrgkqgortq0r56937tnru.apps.googleusercontent.com";
		// 創建 HttpTransport 和 JsonFactory 實例
		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// (Receive idTokenString by HTTPS POST)

		String idTokenStr = idTokenString.split("credential=")[1].split("&g_csrf_token")[0];
		System.out.println("idTokenStr = " + idTokenStr);
		GoogleIdToken idToken = verifier.verify(idTokenStr);

		return idToken;
	}
}
