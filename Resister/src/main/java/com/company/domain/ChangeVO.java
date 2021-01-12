package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeVO {
	private String userid;
	private String password;
	private String new_password;
	private String confirm_password;
	
	public boolean newPasswordEqualsConfirm() {
		return new_password.equals(confirm_password);
	}
}
