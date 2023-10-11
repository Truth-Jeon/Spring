package org.galapagos.domain;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TravelVO {
	private Long no;
	
	@NotBlank(message = "권역은 필수 항목입니다.")
	private String region;
	
	@NotBlank(message = "제목은 필수 항목입니다.")
	private String title;
	
	private String description;
	
	@NotBlank(message = "주소는 필수 항목입니다.")
	private String address;
	
	private String phone;
	
	private String hearts;
	
	private Boolean myHeart;
	
	public String getImage() {
		int i = new Random().nextInt(5) + 1;
		return String.format("/resources/images/travel/%03d-%d.jpg", no, i);
	}
	
	public List<String> getImages() {
		List<String> list = new ArrayList<String>();
		for(int i=1; i<=5; i++) {
			list.add(String.format("/resources/images/travel/%03d-%d.jpg", no, i));
		}
		return list;
	}
	
	public String getSummary() {
		return description.split("<br>")[0].replace("<p>", "");
	}
}
