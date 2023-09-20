package org.galapagos.mapper;

import static org.junit.Assert.*;

import org.galapagos.config.RootConfig;
import org.galapagos.persistence.DataSourceTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})//제일중요함
@Log4j
public class TimeMapperTest {
	//실행되는 순서는 method 배치 순서가 아님.
	
	@Autowired
	private TimeMapper timeMapper; //TimeMapper : 인터페이스

	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName()); //구현클래스
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("getTime2");
		log.info(timeMapper.getTime2());
	}
}
