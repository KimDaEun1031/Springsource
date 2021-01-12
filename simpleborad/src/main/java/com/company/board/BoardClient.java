package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {
	
	public static void main(String[] args) {
	
		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config.xml");
		BoardService service = (BoardService)ctx.getBean("service");
		
		
		//전체 리스트
		List<BoardVO> list = service.getList();
		for(BoardVO vo1:list) {
			System.out.println(vo1.toString());
		}
		
		//게시글 하나 가져오기
		
		
		//게시글 하나 삭제하기
		
		//게시글 수정하기
	}
}
