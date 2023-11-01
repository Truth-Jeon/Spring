package org.galapagos.controller;

import org.galapagos.domain.BasketBallVO;
import org.galapagos.service.BasketBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basketball")
public class BasketBallController {
	@Autowired
	BasketBallService service;
	
	@GetMapping("/predict")
	public void getPredict(BasketBallVO vo) {
	}
	
	@PostMapping("/predict")
	public void postPredict(BasketBallVO vo, Model model) {
		String result = service.predict(vo);
		model.addAttribute("result", result);
		
		//특정 카테고리에 맞는 상품 추천
		
	}
}
