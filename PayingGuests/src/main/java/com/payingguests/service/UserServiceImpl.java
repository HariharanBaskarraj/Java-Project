package com.payingguests.service;

import com.payingguests.dao.IUserDao;
import com.payingguests.dao.UserDaoImpl;
import com.payingguests.exceptions.UserNotFoundException;
import com.payingguests.model.User;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public String register(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User login(String username, String password) {
		User result = userDao.login(username, password);
		if (result == null)
			throw new UserNotFoundException("User is not found in the database. Kindly regiter.");
		return result;
	}

	@Override
	public int changePassword(String username, String oldPassword,String password) {
		int result = userDao.changePassword(username, oldPassword, password);
		if (result == 0)
			throw new UserNotFoundException("User does not exists");
		return result;
	}

}
