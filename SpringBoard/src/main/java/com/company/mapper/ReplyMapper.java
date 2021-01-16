package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyMapper {
	public int insert(ReplyVO reply);
	public ReplyVO read(int rno);
	//두개 이상의 파라메터가 온다면 @param을 붙이기
	public List<ReplyVO> list(@Param("cri")Criteria cri,@Param("bno")int bno);
	public int update(ReplyVO reply);
	
	public int countBno(int bno);
}
