package com.king.myweb.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.king.myweb.domain.BoardVO;
import com.king.myweb.domain.Criteria;
import com.king.myweb.domain.SearchCriteria;
import com.king.myweb.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		boardDAO.write(boardVO);
	}

	// 2. 글 목록
	@Override
	public List<BoardVO> list() throws Exception {
		return boardDAO.list();
	}

	// 3. 글 목록 + 페이지 분할 
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		
		return boardDAO.listPage(cri);
	}

	// 4. 게시물 총 개수 
	@Override
	public int listCount() throws Exception {
		return boardDAO.listCount();
	}

	// 5. 글 상세 조회 
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDAO.read(bno);
	}

	// 6. 글목록 + 페이징 + 검색
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		return boardDAO.listSearch(scri);
	}

}
