package com.king.myweb.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.king.myweb.domain.ReplyVO;
import com.king.myweb.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDAO;
	
	// 1. 댓글 조회
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return replyDAO.readReply(bno);
	}

	// 2. 댓글 작성 
	@Override
	public void writeReply(ReplyVO replyVO) throws Exception {
		replyDAO.writeReply(replyVO);
	}

	// 3. 댓글 수정 
	@Override
	public void replyUpdate(ReplyVO replyVO) throws Exception {
		replyDAO.replyUpdate(replyVO);
	}

	// 4. 특정 댓글 조회 
	@Override
	public ReplyVO readReplySelect(int rno) throws Exception {
		return replyDAO.readReplySelect(rno);
	}

	// 5. 댓글 삭제 
	@Override
	public void replyDelete(ReplyVO replyVO) throws Exception {
		replyDAO.replyDelete(replyVO);
	}

}
