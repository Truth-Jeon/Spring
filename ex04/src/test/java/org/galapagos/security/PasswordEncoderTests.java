package org.galapagos.security;

import org.galapagos.config.RootConfig;
import org.galapagos.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {
		RootConfig.class,
		SecurityConfig.class
})
@Log4j
public class PasswordEncoderTests {
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Test
	public void testEncode() {
		String str = "1234";
		
		String enStr = pwEncoder.encode(str); //회원가입 시 직접 구현
		log.info("password: " + enStr); //암호화 시 : 실제비밀번호+salt -> equals로 비번 확인 못함.
		
		String enStr2 = pwEncoder.encode(str);
		log.info("password: " + enStr2);//인코딩된 비밀번호는 60자임. -> DB에서 핸들링 -> 몇자인지 알아야 함.
		
		//matches는 별로 쓸 일이 없지만, PasswordEncoder는 나중에 회원가입 시 쓸 일이 생김.
		log.info("match : " + pwEncoder.matches(str, enStr)); //matches(사용자 입력값, 암호화 된 값)
		log.info("match : " + pwEncoder.matches(str, enStr2)); // 둘이 같은 값인지 matches로 확인 가능함.
	}
}
