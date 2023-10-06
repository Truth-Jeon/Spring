package org.galapagos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReplyVO {
	private Long no;
	private Long cno;
	
	private String writer;
	private String content;
	private Date regDate;
	private Date updateDate;
}
