package com.king.myweb.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.king.myweb.domain.BoardVO;
import com.king.myweb.domain.Criteria;
import com.king.myweb.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO{

	//mybatis
	@Inject
	private SqlSession sql;
	
	//Mapper
	private static String namespace = "com.king.myweb.mappers.boardMapper";

	//글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sql.insert(namespace+".write", boardVO);
		System.out.println(boardVO.getContent());
	}

	// 2. 글 목록 
	@Override
	public List<BoardVO> list() throws Exception {
		return sql.selectList(namespace+".list");
	}

	// 3. 글 목록 + 페이지 분할 
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return sql.selectList(namespace+".listPage", cri);  // 매개변수를 가지고 Mapper 로 전달 됨 
	}

	// 4. 게시글 총 개수 
	@Override
	public int listCount() throws Exception {
		return sql.selectOne(namespace+".listCount");
	}

	// 5. 글 상세 조회 
	@Override
	public BoardVO read(int bno) throws Exception {
		return sql.selectOne(namespace+".read", bno);
	}

	// 6. 글목록 + 페이징 + 검색
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		return sql.selectList(namespace+".listSearch", scri);
	}

	// 7. 글 수정 modify 
	@Override
	public void update(BoardVO boardVO) throws Exception {
		System.out.println(boardVO.getContent());
		sql.update(namespace+".update", boardVO);
		
	}

	// 8. 글 삭제 delete 
	@Override
	public void delete(int bno) throws Exception {
		sql.delete(namespace+".delete", bno) ;
	}
	

}
