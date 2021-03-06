package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public boolean regist(BoardVO board) {
		return mapper.insert(board)>0?true:false;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean remove(int bno) {
		return mapper.delete(bno)>0?true:false;
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board)>0?true:false;
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.totalCnt(cri);
	}

}
