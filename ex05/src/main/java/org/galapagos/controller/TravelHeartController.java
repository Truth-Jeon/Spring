package org.galapagos.controller;

import org.galapagos.domain.HeartVO;
import org.galapagos.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//@Controller + 모든 메서드에서 @ResponseBody 생성, 응답인코딩 application/json
@RequestMapping("/api/travel/heart")
public class TravelHeartController {
	@Autowired
	TravelMapper mapper;
	
	@PostMapping("/add")
	//@RequestBody : application/json으로 해석 -> a=b&c=d 에서 {a:b, c:d}로 해석하기 위함.
	public HeartVO addHeart(@RequestBody HeartVO heart) {
		mapper.addHeart(heart);
		return heart;
	}
	
	//get, delete는 body가 없음. 그래서 @RequestBody를 못씀. -> delete시 url을 보고 삭제하게 됨.
	@DeleteMapping("/delete")
	public String deleteHeart(HeartVO heart) {
		mapper.deleteHeart(heart);
		return "OK";
	}
}
