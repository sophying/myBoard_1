package com.king.myweb.persistence;

import java.util.List;

import com.king.myweb.domain.BoardVO;
import com.king.myweb.domain.Criteria;
import com.king.myweb.domain.SearchCriteria;

public interface BoardDAO {
	
	//글 작성
	public void write(BoardVO boardVO) throws Exception;

	// 2. 글 목록 
	public List<BoardVO> list()throws Exception;

	// 3. 글 목록 + 페이지 분할 
	public List<BoardVO> listPage(Criteria cri)throws Exception;

	// 4. 게시글 총 개수 
	public int listCount() throws Exception;

	// 5. 글 상세 조회 
	public BoardVO read(int bno) throws Exception;

	// 6. 글 목록 + 페이징 + 검색
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception;

	// 7. 글 수정 modify 
	public void update(BoardVO boardVO) throws Exception;

	// 8. 글 삭제 delete
	public void delete(int bno) throws Exception;
}
