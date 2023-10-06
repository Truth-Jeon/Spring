package org.galapagos.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.core.async.RingBufferLogEvent;
import org.galapagos.domain.MemberVO;
import org.galapagos.service.MemberService;
import org.galapagos.service.MemberServiceImpl;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@RequestMapping("/security")
@Controller
public class SecurityController {
	@Autowired
	MemberService service;
	
	@GetMapping("/login") // => security/login
	public void login() {
		log.info("login page");
	}
	
	@GetMapping("/signup") // => security/login
	public void signup(@ModelAttribute("member") MemberVO member) {
		log.info("signup page");
	}
	
	@PostMapping("/signup") // => security/login
	public String signup(
			@Valid
			@ModelAttribute("member") MemberVO member,
			Errors errors,
			MultipartFile avatar) throws IOException {
		
		// 1. 비밀번호, 비밀번호 확인 일치 여부
		if(!member.getPassword().equals(member.getPassword2())){
			//에러 추가
			errors.rejectValue("password2", "비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
		}
		
		// 2. 아이디 중복
		//username 유효성 검사를 통과했는지 체크하기.
		if(!errors.hasFieldErrors("username")) {
			//DB에서 username을 검사
			if(service.get(member.getUsername()) != null) { //중복됨
				errors.rejectValue("username", "아이디 중복", "이미 사용중인 아이디입니다.");
			}
		}
		
		if(errors.hasErrors()) {
			return "security/signup";
		}
		
		//DB 저장
		service.register(member, avatar);
		
		//DB저장 성공
		return "redirect:/";
	}
	
	@GetMapping("/avatar/{size}/{username}")
	@ResponseBody
	public void avatar(@PathVariable("size") String size,
			@PathVariable("username") String username,
			HttpServletResponse response) throws IOException {
		File src = new File(MemberServiceImpl.AVATAR_UPDATE_DIR, username + ".png");
		if(!src.exists()) { //파일이 존재하지 않으면
			src = new File(MemberServiceImpl.AVATAR_UPDATE_DIR, "unknown.png");
		}
		response.setHeader("Content-Type", "image/png");
		
		if(size.equals("sm")) {
			Thumbnails.of(src)
			.size(25, 25)
			.toOutputStream(response.getOutputStream());
		} else {
			Thumbnails.of(src)
			.size(50, 50)
			.toOutputStream(response.getOutputStream());
		}
	}
	
	@GetMapping("/profile")
	public void profile(){
		
	}
		
}
