package org.galapagos.mapper;

import java.util.List;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.HeartVO;
import org.galapagos.domain.TravelVO;

public interface TravelMapper {
	public int getTotalCount(Criteria cri); //검색기능에서 쓰임.
	public List<TravelVO> getList(Criteria cri); //페이지 목록 추출
	public List<TravelVO> getRandom(int count);
	public void insert(TravelVO travel);//pk 추출 포함
	public TravelVO read(Long no); //bno : PK - where절 구성
	public int delete(Long no); //영향받은 행의 갯수가 리턴됨.
	public int update(TravelVO travel);
	
	//좋아요 처리
	public List<Long> getHeartsList(String username); //tno 목록
	public int addHeart(HeartVO heart); //좋아요 클릭
	public int deleteHeart(HeartVO heart); //좋아요 취소
}