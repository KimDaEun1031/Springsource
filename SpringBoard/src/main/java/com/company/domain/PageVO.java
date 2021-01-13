package com.company.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {
	//페이지 나누기와 관련된 모든 정보를 가지고 있는 객체
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		this.total = total; //전체 게시물수
		this.cri = cri; //현재 페이지 번호, 페이지당 게시물 갯수
		
		//아래의 작업은
		//?번 페이지를 클릭 시 1 2 3 4 5 6 7 8 9 10 나오게 할려고
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = endPage - 9;
		
		// ?번 페이지를 클릭 시 보여줄 게시물이 10개 이하을 경우의 작업
		int realEnd=(int)(Math.ceil((total/1.0)/cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage>1;
		this.next = this.endPage < realEnd;
	}
}
