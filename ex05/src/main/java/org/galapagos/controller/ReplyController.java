package org.galapagos.controller;

import org.galapagos.domain.ReplyVO;
import org.galapagos.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board/{bno}/reply")
public class ReplyController {
	@Autowired
	private ReplyMapper mapper;
	
	@PostMapping("")
	public ReplyVO create(@RequestBody ReplyVO vo) {
		mapper.create(vo);
		return mapper.get(vo.getNo());
	}
	
	@PutMapping("/{no}")
	public ReplyVO update(
			@PathVariable Long no,
			@RequestBody ReplyVO vo) {
		System.out.println("==> " + vo);
		mapper.update(vo);
		return mapper.get(vo.getNo());
	}
	
	@DeleteMapping("/{no}")
	public String delete(@PathVariable Long no) {
		mapper.delete(no);
		return "OK";
	}
	
}
