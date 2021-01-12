package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //@Component, @Service, @Repository와 같은 개념
@RequestMapping("/sample/*")
public class SampleController {
	//일반 컨트롤러 작업
	@RequestMapping("/basic") 
	//actionfactory에서 했던 작업과 비슷한 작업 => (cmd.equals("list.do"))
	public void basic() {
		log.info("basic...."); ///sample/basic => view 리졸버
	}
	
	@RequestMapping("/test") 
	public String test() {
		log.info("test....");
		return "default"; //default => view 리졸버
	}
}
