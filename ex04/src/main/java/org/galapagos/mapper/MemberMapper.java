package org.galapagos.mapper;

import org.galapagos.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
}
