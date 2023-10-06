package org.galapagos.security;

import org.galapagos.domain.CustomUser;
import org.galapagos.domain.MemberVO;
import org.galapagos.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	   private MemberMapper mapper;
	@Override
	public UserDetails loadUserByUsername(String username)//비밀번호는 UserDetails에 담김
	      throws UsernameNotFoundException { //UsernameNotFoundException : 로그인 시도 시 user가 없을 때 호출됨.
	      log.warn("Load User By Username: " + username); //유저 네임이 제대로 전달되는지 확인하기
	      
	      MemberVO vo = mapper.read(username);
	      if(vo == null) {
	    	  throw new UsernameNotFoundException(username + "은 없는 ID입니다.");//오류메세지
	      }
	      
	      log.warn("user vo: " + vo);
	      
	      return new CustomUser(vo);
	}
}
