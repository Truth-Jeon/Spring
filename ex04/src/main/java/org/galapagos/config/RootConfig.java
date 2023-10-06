package org.galapagos.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "org.galapagos.sample" })
@MapperScan(basePackages = { "org.galapagos.mapper" })
public class RootConfig {
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean // 메소드의 리턴값(DataSource)가 빈으로 등록
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/glory_db");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/glory_db");
		config.setUsername("glory");
		config.setPassword("1234");

		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;// 빈 객체
	}

	// @Configuration @Bean 어노테이션이 붙은 메소드는
	// 메소드를 호출한다고 해서 리턴값을 달라는 뜻이 아님.
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

		// dataSource() 메소드가 등록한 Bean을 달라는 뜻.
		sqlSessionFactory.setDataSource(dataSource()); // dataSource() 메서드 호출
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
}