package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("log")   //logAdvice =원래는 이렇게 이름이 들어오지만 그게 싫다면 이름 지정하기("log")
public class LogAdvice {
	public void beforeLog() {
		System.out.println("[공통로그] 비지니스 로직 전 수행");
	}
	public void afterLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 예외와 상관없이 호출");
	}
	public void afterThrowLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 예외와 발생시 호출");
	}
	public void afterReturnLog() {
		System.out.println("[공통로그] 비지니스 로직 후 수행 - 정상 수행시 호출");
	}
	public void aroundLog(ProceedingJoinPoint pip) {
		System.out.println("[공통로그] 비지니스 로직 전 수행");
		
		try {
			pip.proceed(); //원래 수행하려고 하는 메소드 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("[공통로그] 비지니스 로직 후 수행");
	}
}
