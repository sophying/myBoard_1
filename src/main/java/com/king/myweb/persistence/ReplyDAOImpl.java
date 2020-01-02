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
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return sql.selectList(namespace+".readReply", bno);
	}

	@Override
	public void writeReply(ReplyVO replyVO) throws Exception {
		sql.insert(namespace+".writeReply", replyVO);
	}

}
