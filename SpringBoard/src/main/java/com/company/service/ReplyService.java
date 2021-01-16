package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyService {
	public boolean regist(ReplyVO reply);
	public ReplyVO getRead(int rno);
	public List<ReplyVO> getlist(Criteria cri,int bno);
	public boolean updateReply(ReplyVO reply);
}
