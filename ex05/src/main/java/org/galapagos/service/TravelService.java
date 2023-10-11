package org.galapagos.service;

import java.security.Principal;
import java.util.List;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.TravelVO;

public interface TravelService {
	public int getTotal(Criteria cri);
	
	// 페이지 목록 추출
	public List<TravelVO> getList(Criteria cri, Principal principal);
	
	public List<TravelVO> getRandom(int count);
	
	public TravelVO get(Long no, Principal principal);
	
	public void register(TravelVO travel);
	
	public boolean modify(TravelVO travel);
	
	public boolean remove(Long no);
}
