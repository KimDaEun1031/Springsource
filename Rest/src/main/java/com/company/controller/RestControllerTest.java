package com.company.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleVO;

import lombok.extern.slf4j.Slf4j;

@RestController //리턴하는 모든 값들은 실제 값이 됨(jsp 페이지 개념이 아님)
@Slf4j
public class RestControllerTest {
	
	@GetMapping(value="/hello",produces="text/plain; charset=UTF-8") //text/plain; -> 텍스트의 기본형식
	//produces => F12번 네트워크 HEADER의 CONTENT-TYPE을 직접 건드림 /  text/plain; -> 고정값
	public String sayHello() {
		log.info("hello 요청");
		return "Hello World";
	}
	
//	@GetMapping("/sendVO")
//	public SampleVO sendVO() {
//		SampleVO sample = new SampleVO();
//		sample.setMno("12");
//		sample.setFirstName("김");
//		sample.setLastName("다은");
//		return sample;
//	}
	//오류 원인 - No converter
	
	//위에거 오류 나서 컨버터 생성
	//컨버터는 객체를 보냈을 때 브라우저가 이해하는 xml이나 json으로 변환해주는
	@GetMapping(value="/sendVO")
	public SampleVO sendVO() {
		SampleVO sample = new SampleVO();
		sample.setMno("12");
		sample.setFirstName("김");
		sample.setLastName("다은");
		return sample;
	}
}
