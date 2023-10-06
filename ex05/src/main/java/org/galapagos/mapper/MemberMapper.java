package org.galapagos.mapper;

import org.galapagos.domain.AuthVO;
import org.galapagos.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
	
	public void insert(MemberVO member);
	
	public void insertAuth(AuthVO auth);
}