package org.galapagos.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String titleString;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date dueDate;
}
