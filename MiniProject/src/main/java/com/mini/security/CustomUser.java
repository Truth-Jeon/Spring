package com.mini.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mini.domain.MemberVO;

public class CustomUser extends User {
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUsername(), vo.getPassword(), vo.getAuthorities());
		
		this.member = vo;
	}

}