package org.galapagos.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.galapagos.domain.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class UserController {
	//get과 post의 URL을 같게 주면 form태그의 action을 생략할 수 있다.
	@GetMapping("/join")
	public String joinGet() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinPost(UserDTO user) {
		log.info(user);
		//redirect: 이동할 url -> pos에 성공했다면 반드시 해야 함.
		return "redirect:/user/join_result";
	}
	
	@GetMapping("/join_result")
	public String joinResult() {
		return "user/join_result";
	}
}
