package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;
import com.company.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/replies/*")  
public class ReplyController {
	
	@Autowired
	private ReplyService replyserivce;
	
	//produces = MediaType.APPLICATION_JSON_UTF8_VALUE => json으로 변환
	
	@PostMapping("/new") //http://localhost:8087/replies/new+post
	public ResponseEntity<String> create(@RequestBody ReplyVO reply) { 
		log.info("댓글 삽입" + reply);
		return replyserivce.regist(reply)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//오류 @RequestBody ReplyVO reply -> 원래는 ReplyVO reply 이것만 들어있었음
	// json으로 보내기 때문에 @RequestBody를 붙여야 된다고 하는데... 찾아보기
	
	@GetMapping(value = "/{rno}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //http://localhost:8087/replies/1(rno)+get
	public ResponseEntity<ReplyVO> select(@PathVariable("rno") int rno) {
		log.info("댓글 하나 가져오기" + rno);
		return new ResponseEntity<ReplyVO>(replyserivce.getRead(rno),HttpStatus.OK);	
	}
	//"{/rno}" = @PathVariable("rno") 꼭 맞춰주기
	
	@GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)//http://localhost:8087/replies/pages/3067/1
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		log.info("전체 댓글 가져오기" + bno);
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<List<ReplyVO>>(replyserivce.getlist(cri, bno),HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO reply) {
		log.info("댓글 수정");
		
		reply.setRno(rno);
		return replyserivce.updateReply(reply)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
