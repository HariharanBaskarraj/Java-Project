package com.payingguests.dao;

import com.payingguests.model.User;

public interface IUserDao {

	String addUser(User user);

	User login(String username, String password);

	int changePassword(String username, String password);

}
