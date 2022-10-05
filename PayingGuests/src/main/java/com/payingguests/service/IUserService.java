package com.payingguests.service;

import com.payingguests.model.User;

public interface IUserService {
	String register(User user);

	User login(String username, String password);

	int changePassword(String username, String oldPassword,String password);
}
