package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardService {
	public boolean insertBook(BoardVO vo);
	public boolean updateBook(BoardVO vo);
	public boolean deleteBook(int code);
	public BoardVO getRow(int code);
	public List<BoardVO> getList();
}
