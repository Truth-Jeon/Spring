package org.galapagos.mapper;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.galapagos.config.RootConfig;
import org.galapagos.domain.AuthVO;
import org.galapagos.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class MemberMapperTest {
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		MemberVO member = mapper.read("admin");
		log.info(member);
		
		for(AuthVO auth : member.getAuthList()) {
			log.info(auth);
		}
	}

}
