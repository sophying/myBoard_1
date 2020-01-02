package com.king.myweb.domain;


// 검색 조건 유지 클래스 
public class Criteria {
	
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	
	// 생성자 
	public Criteria() {
		
		this.page=1;
		this.perPageNum = 10; //10 개씩 페이지 분할
		
	}
	
	public int getPage() {
		return page;
	}
	
	// 페이지 분할 기준 정의 
	public void setPage(int page) {
		
		if (page <= 0 ) {
			this.page = 1; // 페이지가 0보다 작을 경우 1페이지로 설정
			return;
		}
		this.page = page;
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	// 페이지에 보여질 리스트의 개수 정의 
	public void setPerPageNum(int perPageNum) {
		
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	//
	public int getPageStart() {
		
		return (this.page-1) * perPageNum;
	}
	
	@Override
	public String toString() {
		return "Criteria [ page="+ page+", perPageNum="+perPageNum+", rowStart="+getRowStart()+", rowEnd="+getRowEnd()+" ]";
	}
	
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}
	
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum -1;
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

}
