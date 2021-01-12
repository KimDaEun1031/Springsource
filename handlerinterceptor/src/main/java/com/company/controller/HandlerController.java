package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HandlerController {
	
	@GetMapping("/doA")
	public void doA() {
		log.info("doA 요청");
	}
}
