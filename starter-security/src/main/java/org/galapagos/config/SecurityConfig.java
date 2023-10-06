package org.galapagos.config;

import javax.sql.DataSource;

import org.galapagos.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource; //RootConfig.java 의 dataSource()임.

	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { // PasswordEncoder가 설정되면 모두 암호화 된 패스워드로만 로그인 가능함.
		return new BCryptPasswordEncoder(); // 암호화된 패스워드만 요구하기 때문에.
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
	
		return repo;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		
		http.addFilterBefore(filter, CsrfFilter.class);
		
//		http.authorizeRequests()// 요청에 대한 접근 "권한 설정"
//				.antMatchers("/security/all").permitAll()// 모두 허용
//				.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // 특정 역할에만 허용
//				.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')")// 특정 역할에만 허용
////		.antMatchers("/board/write","/board/modify", "/board/list").authenticated()//로그인 사용자에게만 허용
//		;
		
		//all -> 로그인과 상관없이 모두 접속 가능함
		//authenticated -> 로그인 한 사용자만 접속 가능
		http.authorizeRequests()
		.antMatchers("/security/profile")
		.authenticated();			

		http.formLogin()// 로그인 폼 설정 시작, default
				.loginPage("/security/login?error=login_required") // get요청
				.loginProcessingUrl("/security/login") // post요청, url은 form의 action으로 요청.
				.defaultSuccessUrl("/").failureUrl("/security/login?error=true"); // 로그인 실패 시 이동 페이지, el : param.error

		http.logout() // 로그아웃 설정 시작
				.logoutUrl("/security/logout") // POST요청 url: 로그아웃 호출 url
				.invalidateHttpSession(true) // 세션 invalidate
				.deleteCookies("remember-me", "JSESSION-ID")// 삭제할 쿠키 목록
				.logoutSuccessUrl("/"); // 로그아웃 이후 이동할 페이지 // GET요청 url
		
		http.rememberMe() // remember-me 기능 설정
			.key("Galapagos")
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(7*24*60*60); // 7일
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(customUserService())
		.passwordEncoder(passwordEncoder());
		log.info("configure....................................................");
	}
}
