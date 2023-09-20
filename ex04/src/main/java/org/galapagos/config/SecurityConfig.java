package org.galapagos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() { //PasswordEncoder가 설정되면 모두 암호화 된 패스워드로만 로그인 가능함.
		return new BCryptPasswordEncoder(); //암호화된 패스워드만 요구하기 때문에.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 요청에 대한 접근 "권한 설정"
				.antMatchers("/security/all").permitAll()// 모두 허용
				.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // 특정 역할에만 허용
				.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')")// 특정 역할에만 허용
//		.antMatchers("/board/write","/board/modify", "/board/list").authenticated()//로그인 사용자에게만 허용
		;

		http.formLogin()// 로그인 폼 설정 시작, default
		.loginPage("/security/login") //get요청
		.loginProcessingUrl("/security/login") //post요청, url은 form의 action으로 요청.
		.defaultSuccessUrl("/")
		.failureUrl("/security/login?error=true"); //로그인 실패 시 이동 페이지, el : param.error
		
		http.logout() //로그아웃 설정 시작
		.logoutUrl("/security/logout") //POST요청 url: 로그아웃 호출 url
		.invalidateHttpSession(true) //세션 invalidate
		.deleteCookies("remember-me", "JSESSION-ID")//삭제할 쿠키 목록
		.logoutSuccessUrl("/security/logout"); //로그아웃 이후 이동할 페이지 // GET요청 url
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure....................................................");
		
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("$2a$10$S8ACPrqg/FcJ50gafSunAuuKfbaUFJrzqDEFp4NLVk/cH0hNr.i9O") //noop : no operation의 약자
		.roles("ADMIN");
		
		auth.inMemoryAuthentication()
		.withUser("member")
//		.password("{noop}1234")
		.password("$2a$10$S8ACPrqg/FcJ50gafSunAuuKfbaUFJrzqDEFp4NLVk/cH0hNr.i9O")
		.roles("MEMBER");
	}
}
