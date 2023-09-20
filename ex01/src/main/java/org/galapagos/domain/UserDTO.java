package org.galapagos.domain;

import java.lang.invoke.CallSite;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.ognl.InappropriateExpressionException;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userid;
	private String password;
	private String password2;
	private String email;
	//배열이든 List든 결과는 같음. 개발자 맘대로 정하면 됨.
//	private String[] hobbies;
	private List<String> hobbies;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
}
