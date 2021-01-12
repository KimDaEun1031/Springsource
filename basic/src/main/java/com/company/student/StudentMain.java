package com.company.student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain {

	public static void main(String[] args) {
		//일반으로 개발
//		StudentInfo info = new StudentInfo(new Student("김다은", "23", "1", "4"));
//		info.getStudentInfo();
		
		//프레임 워크로
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		StudentInfo info = (StudentInfo) ctx.getBean("info");
		info.getStudentInfo();

	}

}
