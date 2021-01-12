package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.domain.LoginVO;

import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Log4j2
public class LoginController {
	//
	
	@RequestMapping("/login") //http://localhost:8087/login
	public void login(){
		log.info("login...");
	}
	
	//get => login.jsp, post => 사용자가 입력한 데이터를 가져와서 db 작업
	@GetMapping(value="/login") //http://localhost:8087/login
	public void loginGet(){
		log.info("loginget...");
	}
	//기존에 배웠던 방법대로 사용자 입력값 가져오기 1번째 방법 - 잘 안씀
//	@PostMapping(value="/login") //http://localhost:8087/login
//	public void loginPost(HttpServletRequest request){
//		log.info("loginpost...");
//		log.info("userid   :"+request.getParameter("userid"));
//		log.info("password : "+request.getParameter("password"));
//		
//	}
	
	//매개변수와 name의 값을 맞춰주면 편함 2번째 방법 - 파라메터 처리(단, 이름 맞추기)
	//단점 : 파라메터가 많아지면 적기가 어렵기에 파라메터가 많다면 좋지 않음
//	@PostMapping(value="/login") //http://localhost:8087/login
//	public void loginPost(String userid, String password){
//		log.info("loginpost...");
//		log.info("userid   :"+userid);
//		log.info("password : "+password);
//	}
	
	//3번째 방법
	@PostMapping(value="/login") //http://localhost:8087/login
	public void loginPost(LoginVO vo){
		log.info("loginpost...");
		log.info("userid   :" +vo.getUserid());
		log.info("password : "+vo.getPassword());
	}
	
	//2,3번 방법을 많이 쓰고 3번 방법을 제일 많이 쓴다.

}
