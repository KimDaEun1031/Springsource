package com.company.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
