package com.sunilos.ecom.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseForm;

public class SellerLoginForm extends BaseForm{

	
	@NotEmpty(message = "Please Enter Your Email-Id")
	@Email
	private String loginId;

	@NotEmpty(message = "Please Enter Your Password")
	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
