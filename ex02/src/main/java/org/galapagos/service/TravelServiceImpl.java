package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.Criteria;
import org.galapagos.domain.TravelVO;
import org.galapagos.mapper.BoardMapper;
import org.galapagos.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service //새로운 어노테이션 등장! 빈 등록이 일어남. (빼먹는 실수 하지 말자.)
@AllArgsConstructor //가독성을 중요시하는 사람들은 생성자를 주입해줌.
public class TravelServiceImpl implements TravelService {
	@Autowired
	private TravelMapper mapper;
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<TravelVO> getList(Criteria cri) {
		return mapper.getList(cri);
	}

	@Override
	public TravelVO get(Long no) {
		// 조회수 처리
		return mapper.read(no);
	}

	@Override
	public void register(TravelVO travel) {
		mapper.insert(travel);
		// 첨부파일 업로드
	}

	@Override
	public boolean modify(TravelVO travel) {
		return mapper.update(travel) == 1;
	}

	@Override
	public boolean remove(Long no) {
		return mapper.delete(no) == 1;
	}

}
