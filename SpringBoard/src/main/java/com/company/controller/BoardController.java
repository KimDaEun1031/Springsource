package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//게시글 작성 폼 보여주기
	@GetMapping("/register")
	public void register() {
		log.info("게시글 작성 페이지 요청...");
	}
	
	//게시글 작성
	@PostMapping("/register")
	public String registerPost(BoardVO board) {
		log.info("게시글 작성 요청..." + board);
		if(service.regist(board)) {
			return "redirect:list";
		} else {
			return "redirect:/";
		}
	}
	
	//게시글 보기
	@GetMapping("/list")
	public void list(Model model) {
		log.info("게시글 전체 조회 요청...");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	//특정 게시물 보기
	@GetMapping({"/read","/modify"})
	public void get(int bno, Model model) {
		log.info("특정 게시물 보기..." + bno);
		BoardVO board = service.getRow(bno);
		model.addAttribute("board", board);
		
	}
	
	//특정 게시물 삭제
	@GetMapping("/remove")
	public String remove(int bno) {
		//성공시 리스트
		log.info("게시물 삭제..." + bno);
		service.remove(bno);
		return "redirect:list";
	}
	
	//특정 게시물 수정
	@PostMapping("/modify")
	public String modify(BoardVO board) {
		log.info("게시물 수정..." + board);
		service.modify(board);
		return "redirect:list";
	}
}
