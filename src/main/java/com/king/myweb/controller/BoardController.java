package com.king.myweb.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.myweb.domain.BoardVO;
import com.king.myweb.domain.Criteria;
import com.king.myweb.domain.PageMaker;
import com.king.myweb.domain.ReplyVO;
import com.king.myweb.domain.SearchCriteria;
import com.king.myweb.service.BoardService;
import com.king.myweb.service.ReplyService;
import com.sun.tracing.dtrace.ModuleName;

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
	public void getWrite(HttpSession session, Model model) throws Exception {
		logger.info("-------[ 1. 글 작성 GET ]");

		Object loginInfo = session.getAttribute("member");

		if (loginInfo == null) {
			model.addAttribute("msg", false);
		}

	}

	// 1. 글 작성 post
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardVO boardVO) throws Exception {
		logger.info("-------[ 1. 글 작성 post POST ]");

		service.write(boardVO);

		return "redirect:/";

	}

	// 2. 글 목록 list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("-------[ 2. 글 목록 list GET ]");

		List<BoardVO> list = service.list();

		model.addAttribute("list", list);
	}

	// 3. 글목록 + 페이지 분할
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("-------[ 3. 글목록 + 페이지 분할 GET ]");

		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount()); // 게시글 총 개수
		model.addAttribute("pageMaker", pageMaker);
	}

	// 4. 글 상세보기 조회
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void getRead(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("-------[ 4. 글 상세 조회 GET ] ");

		BoardVO boardVO = service.read(bno);
		model.addAttribute("read", boardVO);
		model.addAttribute("scri", scri);

		List<ReplyVO> repList = RepService.readReply(bno);
		model.addAttribute("repList", repList);
	}

	// 5. 글목록 + 페이징 + 검색
	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("-------[ 5. 글목록 + 페이징 + 검색 GET ] ");

		List<BoardVO> list = service.listSearch(scri);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount());
		model.addAttribute("pageMaker", pageMaker);
	}

	// 6. modify 글 수정 GET
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("-------[ 6. modify 글 수정 GET ] ");
		BoardVO boardVO = service.read(bno);
		model.addAttribute("modify", boardVO);
		model.addAttribute("scri", scri);

	}

	// 6. modify 글 수정 Post
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String postModify(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("-------[ 6. modify 글 수정 POST ] ");

		service.update(boardVO);

		System.out.println(boardVO.getContent());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/listSearch";
	}

	// 7. 글 삭제 delete GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void getDelete(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("-------[ 7. 글 삭제 delete GET ] ");

		model.addAttribute("delete", bno);
		model.addAttribute("scri", scri);
	}

	// 7. 글 삭제 delete POST
	/*
	 * RedirectAttributes : Model 역할을 가지고 있음 모든 플레시 속성을 저장 후 session 의 속성으로 model로
	 * 담아 전달 함
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postDelete(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri,
			RedirectAttributes rttr) throws Exception {
		logger.info("-------[ 7. 글 삭제 delete POST ] ");

		service.delete(bno);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/listSearch";
	}

	// 8. reply Write 댓글 작성 Post
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		logger.info("-------[ 8. reply Write 댓글 작성 POST ] ");

		RepService.writeReply(replyVO);

		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/read";
	}

	// 9. 댓글 수정 GET
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.GET)
	public void getReplyUpdate(@RequestParam("rno") int rno, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {

		logger.info("-------[ 9. 댓글 수정 GET ] ");

		ReplyVO replyVO = null;

		replyVO = RepService.readReplySelect(rno);

		model.addAttribute("readReply", replyVO);
		model.addAttribute("scri", scri);
	}

	// 9. 댓글 수정 POST
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		logger.info("-------[ 9. 댓글 수정 POST ] ");

		RepService.replyUpdate(replyVO);

		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/read";
	}

	
	  // 10. 댓글 삭제 GET
/*	  
	  @RequestMapping(value = "/replyDelete", method = RequestMethod.GET) public
	  void getReplyDelete(@RequestParam("bno") int bno, @ModelAttribute("scri")
	  SearchCriteria scri, Model model) throws Exception{
	  logger.info("-------[ 10. 댓글 삭제 GET ] ");
	  
	  model.addAttribute("replyDelete",bno); 
	  model.addAttribute("scri", scri);
	  
	  }
*/
	  @RequestMapping(value = "/replyDelete", method = RequestMethod.GET) public
	  void getReplyDelete(@RequestParam("rno") int rno, @ModelAttribute("scri")
	  SearchCriteria scri, Model model) throws Exception{
	  logger.info("-------[ 10. replyDelete  댓글 삭제 GET ] ");
	  
	  ReplyVO replyVO = null;
	  
	  replyVO = RepService.readReplySelect(rno);
	  
	  model.addAttribute("readReply", replyVO); 
	  model.addAttribute("scri", scri);
	  
	  }
	
	
	// 10. 댓글 삭제 POST
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	public String postReplyDelete(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("-------[ 10. 댓글 삭제 POST ] ");

		RepService.replyDelete(replyVO);

		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/read";
	}

}
