package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO {
	//로그인 성공시 userid와 name 정보만 담는 객체
	private String userid;
	private String name;
}
