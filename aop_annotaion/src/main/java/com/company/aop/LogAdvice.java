package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("log")   //logAdvice =원래는 이렇게 이름이 들어오지만 그게 싫다면 이름 지정하기("log")
@Aspect //참견이 들어가는 
public class LogAdvice {
	
	@Before(value="execution(* com.company.aop.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[before] 비지니스 로직 전 수행");
	}
	@After(value="execution(* com.company.aop.Product.getInfo())")
	public void afterLog() {
		System.out.println("[afterLog] 비지니스 로직 후 수행 - 예외와 상관없이 호출");
	}
	@AfterThrowing(value="execution(* com.company.aop.Product.getInfo())")
	public void afterThrowLog() {
		System.out.println("[afterThrowing] 비지니스 로직 후 수행 - 예외와 발생시 호출");
	}
	@AfterReturning(value="execution(* com.company.aop.Product.getInfo())")
	public void afterReturnLog() {
		System.out.println("[afterReturn] 비지니스 로직 후 수행 - 정상 수행시 호출");
	}
	@Around(value="execution(* com.company.aop.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pip) {
		System.out.println("[around] 비지니스 로직 전 수행");
		
		try {
			pip.proceed(); //원래 수행하려고 하는 메소드 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("[around] 비지니스 로직 후 수행");
	}
}
