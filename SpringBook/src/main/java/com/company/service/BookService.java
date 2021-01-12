package com.company.service;

import java.util.List;

import com.company.domain.BookVO;


public interface BookService {
	public boolean insertBook(BookVO book);
	public List<BookVO> getList();
	public boolean deleteBook(int code);
	public boolean updateBook(int code, int price);
	public List<BookVO> searchList(String criteria, String keyword);
}
