package com.company.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.PersonVO;
import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService) ctx.getBean("person");
		//확인 할 땐 꼭 주석으로..
		//insert
		//service.insertPerson("hong124", "강민섭");
		//update
		//service.updatePerson("hong124", "랑아디");
		//delete
		//service.deletePerson("hong124");
		
		//select
		System.out.println(service.get("hong124"));
		//selectall
		System.out.println("========================");
		List<PersonVO> list = service.list();
		for(PersonVO vo:list) {
			System.out.println(vo);
		}
	}

}
