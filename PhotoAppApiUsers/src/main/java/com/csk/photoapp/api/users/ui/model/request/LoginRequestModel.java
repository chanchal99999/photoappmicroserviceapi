package com.csk.photoapp.api.users.ui.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LoginRequestModel implements Serializable {

	private static final long serialVersionUID = -5741276368752065771L;

	@NotNull(message = "First name can't be null")
	private String email;

	@NotNull(message = "Password can't be null")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
