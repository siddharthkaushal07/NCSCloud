package com.sunilos.common.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sunilos.common.BaseForm;

/**
 * Contains change password form elements and their declarative input
 * validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class ChangePasswordForm extends BaseForm {

	@NotEmpty
	@Size(min = 4, max=20)
	private String oldPassword;

	@NotEmpty
	@Size(min = 4, max=20)
	private String newPassword;
	
	@NotEmpty
	@Size(min = 4, max=20)
	private String confirmPassword;
	
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
