package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService)ctx.getBean("service");
		
		//도서 정보 입력
		BookVO insertVO = new BookVO(1010, "안녕하세요", "김다은", 344000);
		if(service.insertBook(insertVO)) {
			System.out.println("도서입력성공");
		}
		
		//전체 리스트 가져오기
		List<BookVO> list = service.getList();
		for(BookVO vo : list) {
			System.out.println(vo);
		}
		

	}

}
