package com.king.myweb.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage; 
	private boolean prev; // 이전
	private boolean next; // 다음 
	
	private int displayPageNum = 10;
	private Criteria cri;
	
	/* getter setter */
	
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	// calcData() 추가 
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		
		/*  ( 현재 페이지 번호 / 10 ) * 10  */
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum)* displayPageNum);
		startPage = (endPage - displayPageNum )+ 1;
		
		/* (double)cri.getPerPageNum() : 한 페이지당 보여줄 게시글의 개수 */
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}
	
	public String makeQuery(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
										.queryParam("perPageNum", cri.getPerPageNum()).build();
		
		return uriComponents.toUriString();
		
	}
	/*
		UriComponents : uri 를 동적으로 생성해주는 클래스
						파라미터가 조합된  uri 를 손쉽게 만들어 주어 콬드상에서 직접 문자열을 조합할 때 생기는 실수를 방지 할 수 있음
		http://dlwjdcks5343.tistory.com/93				
	*/
	
	public String makeSearch(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
										.queryParam("perPageNum",  cri.getPerPageNum())
										.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
										.queryParam("keyword",encoding(((SearchCriteria)cri).getKeyword()))
										.build();		
		return uriComponents.toUriString();
	}
	
	public String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword,"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}

	

}
