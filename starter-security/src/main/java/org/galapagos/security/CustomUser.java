package org.galapagos.security;

import java.util.Collection;

import org.galapagos.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemberVO member;
	
	public CustomUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUsername(), vo.getPassword(), vo.getAuthorities());
		this.member = vo;
	}
}
