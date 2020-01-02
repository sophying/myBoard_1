package com.king.myweb.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.myweb.domain.BoardVO;
import com.king.myweb.domain.Criteria;
import com.king.myweb.domain.PageMaker;
import com.king.myweb.domain.ReplyVO;
import com.king.myweb.domain.SearchCriteria;
import com.king.myweb.service.BoardService;
import com.king.myweb.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	ReplyService RepService;
	
	// 1. 글 작성 get
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite(HttpSession session, Model model) throws Exception{
		logger.info("-------[ 1. 글 작성 get ]");
		
		Object loginInfo = session.getAttribute("member");
		
		if (loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}
	
	
	// 1. 글 작성 post
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardVO boardVO)throws Exception{
		logger.info("-------[ 1. 글 작성 post ]");
		
		service.write(boardVO);
		
		return "redirect:/";
		
	}
	
	// 2. 글 목록 list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception{
		logger.info("-------[ 2. 글 목록 list ]");
		
		List<BoardVO> list = service.list();
		
		model.addAttribute("list", list);
	}
	
	// 3. 글목록 + 페이지 분할
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info("-------[ 3. 글목록 + 페이지 분할 ]");
		
		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount()); // 게시글 총 개수 
		model.addAttribute("pageMaker", pageMaker);
	}
	
	// 4. 글 상세보기 조회
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void getRead(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model ) throws Exception {
		logger.info("-------[ 4. 글 상세 조회 ] ");
		
		BoardVO boardVO = service.read(bno);
		model.addAttribute("read", boardVO);
		model.addAttribute("scri", scri);
		
		List<ReplyVO> repList = RepService.readReply(bno);
		model.addAttribute("repList", repList);
	}
	
	// 5. 글목록 + 페이징 + 검색
	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("-------[ 5. 글목록 + 페이징 + 검색 ] ");
		
		List<BoardVO> list = service.listSearch(scri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount());
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
}
