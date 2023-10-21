package com.mini.domain;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class MemberVO {
	@NotBlank(message = "ID는 필수항목입니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수항목입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;
	
	@NotBlank(message = "이름은 필수항목입니다.")
	private String name;
	
	@NotBlank(message = "닉네임은 필수항목입니다.")
	private String nickname;
	
	@NotBlank(message = "휴대폰 번호는 필수항목입니다.")
	private String cellPhone;
	
	@NotBlank(message = "EMAIL은 필수항목입니다.")
	@Email(message = "EMAIL형식이 올바르지 않습니다.")
	private String email;
	
	private Date regDate;
	
	private Date updateDate;
	
	private List<AuthVO> authList;
	
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(AuthVO auth: authList) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
		
		return authorities;
	}
}
