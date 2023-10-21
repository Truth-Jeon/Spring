package com.mini.mapper;

import com.mini.domain.AuthVO;
import com.mini.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
	public void insert(MemberVO member);
	public void insertAuth(AuthVO auth);
}
