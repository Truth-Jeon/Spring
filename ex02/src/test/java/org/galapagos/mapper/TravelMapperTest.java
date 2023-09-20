package org.galapagos.mapper;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.galapagos.config.RootConfig;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.TravelVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class TravelMapperTest {
	@Autowired
	private TravelMapper mapper;
	
	//@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		
		int total = mapper.getTotalCount(cri);
		log.info("total: " + total);
		
		cri.setType("RTD");
		cri.setKeyword("해수욕장");
		total = mapper.getTotalCount(cri);
		log.info("total: " + total);
	}

	//@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		
		List<TravelVO> list= mapper.getList(cri);
		for(TravelVO travel: list) {
			log.info(travel);
		}
		
		//검색
		cri.setType("RTD");
		cri.setKeyword("해수욕장");
		list = mapper.getList(cri);
		for(TravelVO travel: list) {
			log.info(travel);
		}
	}

	//@Test
	public void testInsert() {
		TravelVO travel = new TravelVO();
		travel.setRegion("수도권");
		travel.setTitle("강남 에코파크");
		travel.setDescription("강남에 위치한 에너지 파크");
		travel.setAddress("서울시 강남구 ");
		travel.setPhone("1111-2222-3333");

		mapper.insert(travel);
		log.info(travel);
	}

	//@Test
	public void testRead() {
		// 존재하는 게시물 번호로 테스트
		TravelVO travel = mapper.read(10L);
		log.info(travel);
	}

	@Test
	public void testDelete() {
		mapper.delete(113L);
	}

	//@Test
	public void testUpdate() {
		TravelVO travel = mapper.read(113L);
		travel.setTitle("강남 에너지 파크");
		travel.setDescription("강남의 떠오르는 명소");
		travel.setAddress("서울시 강남구 개포동");
		travel.setPhone("222-2222-222");
		mapper.update(travel);
	}

}
