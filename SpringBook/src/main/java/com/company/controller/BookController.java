package com.company.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/insert")
	public String insert(BookVO book, RedirectAttributes rttr) {
		log.info("도서 정보 입력 요청" + book);
		
		try {
			if(service.insertBook(book)) {
				return "redirect:/select";
			} else {
				return "redirect:/";
			}			
		} catch (Exception e) { //실패시
			rttr.addFlashAttribute("tab", "insert");
			return "redirect:/";
		}
	}
	
	//전체 리스트
	@GetMapping("/select")
	public String selectAll(Model model) {
		log.info("전체 리스트 가져오기");
		
		//서비스 작업 호출
		List<BookVO> list = service.getList();
		model.addAttribute("list", list);
		
		return "book_selectAll";
	}
	
	//도서정보 삭제
	@PostMapping("/delete")
	public String deletePost(int code, RedirectAttributes rttr) {
		log.info("도서 정보 삭제 요청" + code);
		if(service.deleteBook(code)) {
			return "redirect:/select";
		} else {
			rttr.addFlashAttribute("tab", "delete");
			return "redirect:/";
		}
	}
	
	
	
	//도서정보 수정
	@PostMapping("/modify")
	public String updatePost(@Param("code")int code, @Param("price")int price, RedirectAttributes rttr) {
		//@Param("") => 이번에는 안 써도 되지만 정확한 파라메터의 이름을 알려주기 위해 사용
		log.info("도서 정보 삭제 요청" + code + "|" +price);
		if(service.updateBook(code, price)) {
			return "redirect:/select";
		} else {
			rttr.addFlashAttribute("tab", "modify");
			return "redirect:/";
		}
	}
	
	//도서정보 찾기
	@PostMapping("/search")
	public String search(String criteria, String keyword, Model model, RedirectAttributes rttr) {
		log.info("도서정보 검색" +criteria+"||"+keyword);
		List<BookVO> list = service.searchList(criteria,keyword);
		if(!list.isEmpty()) { //list.size() > 0 으로 해도 됨
			model.addAttribute("list", list);
			return "book_searchAll";
		} else { //아무것도 없을 때(실패)
			rttr.addFlashAttribute("tab", "search");
			return "redirect:/";
		}
	}
	
	@GetMapping("/search")
	public String searchGet(RedirectAttributes rttr) {
		log.info("search From 요청");
		rttr.addFlashAttribute("tab", "search");
		return "redirect:/";
	}
}
