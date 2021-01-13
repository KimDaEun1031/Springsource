package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.PageVO;
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
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("게시글 작성 요청..." + board);
		if(service.regist(board)) {
			log.info("게시글 번호 :"+board.getBno());
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:list";
		} else {
			return "redirect:/";
		}
	}
	
	//게시글 목록 보기
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("게시글 전체 조회 요청...");
		//전체 목록 요청
		List<BoardVO> list = service.getList(cri);
		//전체 게시물 수 요청
		int total = service.getTotal();
		model.addAttribute("list", list);
		model.addAttribute("pageVO", new PageVO(cri, total));
	}
	
	//특정 게시물 보기
	@GetMapping({"/read","/modify"})
	public void get(int bno, Model model,Criteria cri) {
		log.info("특정 게시물 보기..." + bno); //단일 조회
		log.info("Criteria"+cri);
		BoardVO board = service.getRow(bno);
		model.addAttribute("board", board);
		
	}
	
	//특정 게시물 삭제
	@GetMapping("/remove")
	public String remove(int bno, RedirectAttributes rttr, Criteria cri) {
		//성공시 리스트
		log.info("게시물 삭제..." + bno);
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("pageNum", cri.getPageNum());
		rttr.addFlashAttribute("amount", cri.getAmount());
		
		return "redirect:list";	
	}
	
	//특정 게시물 수정
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr,Criteria cri) {
		log.info("게시물 수정..." + board);
		service.modify(board);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("pageNum", cri.getPageNum());
		rttr.addFlashAttribute("amount", cri.getAmount());
		
		return "redirect:list";
	}
}
