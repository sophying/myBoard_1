package com.king.myweb.service;

import java.util.List;

import com.king.myweb.domain.ReplyVO;

public interface ReplyService {
	
	//1. 댓글 조회
	public List<ReplyVO> readReply(int bno) throws Exception;

	//2. 댓글 작성
	public void writeReply(ReplyVO replyVO) throws Exception;
	
	//3. 댓글 수정
	public void replyUpdate(ReplyVO replyVO) throws Exception;

	//4. 특정 댓글 조회 
	public ReplyVO readReplySelect(int rno) throws Exception;
	
	// 5. 댓글 삭제
	public void replyDelete(ReplyVO replyVO ) throws Exception;
	 

}
