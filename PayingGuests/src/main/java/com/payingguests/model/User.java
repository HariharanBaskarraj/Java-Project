package com.payingguests.model;

public class User {
	private String username;
	private String name;
	private String mobile;
	private String email;
	private String city;
	private String password;

	public User() {
		super();
	}

	public User(String username, String name, String mobile, String email, String city, String password) {
		super();
		this.username = username;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.city = city;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", city="
				+ city + ", password=" + password + "]";
	}

}
