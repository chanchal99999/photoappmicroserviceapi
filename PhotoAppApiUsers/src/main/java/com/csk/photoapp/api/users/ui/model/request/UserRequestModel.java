package com.csk.photoapp.api.users.ui.model.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestModel implements Serializable {

	private static final long serialVersionUID = 3562657142183227809L;

	private String userId;

	@NotNull(message = "First name can't be null")
	@Size(min = 2, message = "First name must not less than two character")
	private String firstName;

	@NotNull(message = "Last name can't be null")
	@Size(min = 2, message = "Last name must not less than two character")
	private String lastName;

	@NotNull(message = "Email can't be null")
	@Email
	private String email;

	@NotNull(message = "Password can't be null")
	@Size(min = 8, max = 16, message = "Password must be equal or greater than 8 character and less than 16 character")
	private String password;

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
