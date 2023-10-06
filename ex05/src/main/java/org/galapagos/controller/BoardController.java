package org.galapagos.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.rmi.activation.ActivationGroup_Stub;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.javassist.expr.NewArray;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.PageDTO;
import org.galapagos.service.BoardService;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	@Autowired
	private BoardService service;
	
	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택 --");
		map.put("T", "제목");
		map.put("C", "내용");
		map.put("W", "작성자");
		map.put("TC", "제목+내용");
		map.put("TW", "제목+작성자");
		map.put("TWC", "제목+작성자+내용");
		
		return map;
	}
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list");
//		model.addAttribute("list", service.getList());
//	}
	
	@GetMapping("/list")
	public void list(
			@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total : " + total);
		
//		model.addAttribute("pageMaker", new PageDTO(cri, 276));//임의로 123 요청
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("board") BoardVO board) {
		log.info("register");
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("board") BoardVO board, Errors errors, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		if(errors.hasErrors()) {
			return "board/register";
		}
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(
			@RequestParam("bno") Long bno,
			@ModelAttribute("cri") Criteria cri,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(
			BoardVO board,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addAttribute("bno", board.getBno());
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
		}
		
//		return "redirect:/board/get?bno=" + board.getBno();
//		return "redirect:/board/get";
		return "redirect:" + cri.getLinkWithBno("/board/get", board.getBno());
	}
	
	@PostMapping("/remove")
	public String remove(
			@RequestParam("bno") Long bno,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		//redirect로 데이터를 넘기면 기존 scope가 사라져서 데이터를 넘기기가 힘듦.
		//만약에 데이터를 여러개 넘겨야 하는 경우에는 Redirect()를 쓰기 힘드므로,
		//이때 사용하는 것이 RedirectAttributes이다.
		log.info("remove........" + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); //Flash : 1회성이라는 뜻.
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
		}
//		return "redirect:/board/list";
		return "redirect:" + cri.getLink("/board/list");
	}
}