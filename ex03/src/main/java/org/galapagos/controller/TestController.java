package org.galapagos.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.galapagos.domain.MemberVO;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/test")
@Log4j
public class TestController {
	//private int[] years = {2000, 2001, 2002, 2003};
//	private String[] hobbies = {"야구", "축구", "배구"};
	private String[] genders = {"남자", "여자"};
	
	//1950년부터 2023년도까지 구성해보세요.
	@ModelAttribute("years")
	public List<Integer> years() { //자동으로 메서드 호출됨, 리턴값이 모델에 추가됨
//		return new int[] {2000, 2001, 2002, 2003};
		List<Integer> years = new ArrayList<Integer>();
		for(int i = 1950; i<=2023; i++) {
			years.add(i);
		}
		return years;
	}
	
	@ModelAttribute("roles")
	public Map<String, String> getRoles() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("ROLE_ADMIN", "Admin");
		map.put("ROLE_MANAGER", "관리자");
		map.put("ROLE_MEMBER", "일반회원");
		
		return map;
	}
	
	@ModelAttribute("hobbies")
	public Map<String, String> getHobbies() {
		Map<String, String> hobbies = new LinkedHashMap<>();
		hobbies.put("BASEBALL", "야구");
		hobbies.put("FOOTBALL", "축구");
		hobbies.put("VOLLYBALL", "배구");
		return hobbies;
	}
	
//	@ModelAttribute("genders")
//	public String[] genders() {
//		return new String[] {"남자", "여자"};
//	}
	
	@GetMapping("/join")
	public void join(
			@ModelAttribute("member") MemberVO member, Model model
			) {
//		member.setNo(10L);
//		model.addAttribute("years", years);
//		model.addAttribute("hobbies", getHobbies());
		model.addAttribute("genders", genders);
	}
	
	//파라미터 값 member :
	//m = new MemberVO();
	//m.setNo(no);
	//m.setUsername(username);
	//m.setPassword(password);
	//m.setPassword2(password2);
	// => handler에 의해서 진행됨.
	@PostMapping("/join")
	public String join(
			@Valid
			@ModelAttribute("member") MemberVO member,
			Errors errors,
			Model model) {
		log.info("POST: " + member);
//		model.addAttribute("years", years);
//		model.addAttribute("hobbies", getHobbies());
		
		if(!member.getPassword().equals(member.getPassword2())) {
			errors.rejectValue("password2", "비밀번호 확인 에러", "비밀번호 확인이 일치하지 않습니다.");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("genders", genders);
			return "test/join"; //forwarding --> form을 다시 입력하도록 함(이전 값을 복원)
			//이 기법을 사용하려면 GET에서도 모델 객체를 넘겨줘야 함.
		}
		
		//DB 처리 작업
		return "redirect:/";
	}
}
