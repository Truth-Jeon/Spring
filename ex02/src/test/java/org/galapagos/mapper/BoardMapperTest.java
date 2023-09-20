package org.galapagos.mapper;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.galapagos.config.RootConfig;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;

	//@Test
	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
		List<BoardVO> list = mapper.getList();

		for (BoardVO b : list) {
			log.info("결과" + b);
		}
	}

	//@Test
	public void testInsert() {
		for(int i=0; i<260; i++) {
			BoardVO board = new BoardVO();
			board.setTitle("새로 작성하는 글");
			board.setContent("새로 작성하는 내용" + i);
			board.setWriter("newbie");
	
			mapper.insert(board);
		}

//		log.info(board);
	}

//	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");

		mapper.insertSelectKey(board);

		log.info(board);
	}

	// @Test
	public void testRead() {
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);
		log.info(board);
	}

	// @Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(2L));
	}

	// @Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");

		int count = mapper.update(board);

		log.info("UPDATE COUNT : " + count);
	}

	//@Test
	public void testPaging() {
//		Criteria cri = new Criteria();
		Criteria cri = new Criteria(3, 3);

		List<BoardVO> list = mapper.getListWithPaging(cri);

		for (BoardVO board : list) {
			log.info(board);
		}
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		
		cri.setKeyword("새로");
		cri.setType("TC"); //제목, 내용에서 검색
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		for(BoardVO board: list) {
			log.info(board);
		}
	}
}
