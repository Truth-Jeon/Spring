package com.mini.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mini.config.RootConfig;
import com.mini.domain.AuthVO;
import com.mini.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		RootConfig.class
})
@Log4j
public class MemberMapperTests {
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		MemberVO member = mapper.read("admin");
		
		log.info(member);
		
		for(AuthVO auth: member.getAuthList()) {
			log.info(auth);
		}
	}

}
