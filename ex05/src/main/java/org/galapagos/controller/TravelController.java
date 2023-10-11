package org.galapagos.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.PageDTO;
import org.galapagos.domain.TravelVO;
import org.galapagos.service.BoardService;
import org.galapagos.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/travel")
@AllArgsConstructor
public class TravelController {
	@Autowired
	private TravelService service;
	
	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택 --");
		map.put("R", "권역");
		map.put("T", "제목");
		map.put("D", "내용");
		map.put("TD", "제목+내용");
		map.put("TR", "권역+제목");
		map.put("TRD", "권역+제목+내용");
		
		return map;
	}

	@GetMapping("/list")
	public void list(
			@ModelAttribute("cri") Criteria cri,
			Principal principal,
			Model model) {
		int total = service.getTotal(cri);
		model.addAttribute("list", service.getList(cri, principal));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping({ "/get", "/modify" })
	public void get(
			@RequestParam("no") Long no,
			@ModelAttribute("cri") Criteria cri,
			Principal principal,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("travel", service.get(no, principal));
	}

	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("travel") TravelVO travel,
			Errors errors,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		
		if(errors.hasErrors()) {
			return "travel/modify";
		}

		service.modify(travel);
		
		return "redirect:" + cri.getLink("/travel/get") + "&no=" + travel.getNo();
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("travel") TravelVO travel) {
		
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("travel") TravelVO travel,
			Errors errors,
			RedirectAttributes rttr) {
		
		if(errors.hasErrors()) { //유효성 검사 실패하면
			return "travel/register"; //view의 이름 리턴 - forwarding
		}
		
		service.register(travel);
		
		return "redirect:/travel/list";
	}
	
	@PostMapping("/remove")
	public String remove(
			@RequestParam("no") Long no,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		service.remove(no);
		return "redirect:/travel/list" + cri.getLink();
	}
}
