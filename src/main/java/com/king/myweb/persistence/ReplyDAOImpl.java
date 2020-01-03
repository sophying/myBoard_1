package com.king.myweb.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.king.myweb.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession sql;
	
	//Mapper
	private static String namespace = "com.king.myweb.mappers.replyMapper";
	
	// 1. 댓글 조회  
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return sql.selectList(namespace+".readReply", bno);
	}

	// 2. 댓글 작성 
	@Override
	public void writeReply(ReplyVO replyVO) throws Exception {
		sql.insert(namespace+".writeReply", replyVO);
	}

	// 3. 댓글 수정 
	@Override
	public void replyUpdate(ReplyVO replyVO) throws Exception {
		sql.update(namespace+".updateReply", replyVO);
	}

	// 4. 특정 댓글 조회 
	@Override
	public ReplyVO readReplySelect(int rno) throws Exception {
		return sql.selectOne(namespace+".readReplySelect", rno);
	}

	// 5. 댓글 삭제 
	@Override
	public void replyDelete(ReplyVO replyVO) throws Exception {
		sql.delete(namespace+".deleteReply", replyVO);
	}

}
