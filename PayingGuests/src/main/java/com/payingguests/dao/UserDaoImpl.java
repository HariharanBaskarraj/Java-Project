package com.payingguests.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import com.payingguests.model.User;
import com.payingguests.util.DbConnection;
import com.payingguests.util.Queries;

public class UserDaoImpl implements IUserDao {

	/**
	 * This method is responsible for creating a new User and generating a random
	 * password.
	 * 
	 * @author HariharanB
	 * @param user 		The user object that contains all the user details.
	 * @return password A random password generated by the passay package.
	 */
	@Override
	public String addUser(User user) {
		// Password Generation
		CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase);
		lowerCase.setNumberOfCharacters(2);
		CharacterRule upperCase = new CharacterRule(EnglishCharacterData.UpperCase);
		upperCase.setNumberOfCharacters(2);
		CharacterRule digit = new CharacterRule(EnglishCharacterData.Digit);
		digit.setNumberOfCharacters(2);
		PasswordGenerator passGenerator = new PasswordGenerator();
		String password = passGenerator.generatePassword(8, lowerCase, upperCase, digit);

		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.INSERTUSERQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getName());
			statement.setString(3, user.getMobile());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getCity());
			statement.setString(6, password);
			statement.execute();
			System.out.println("User added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return password;
	}

	/**
	 * This method checks whether the user is present by validating the username and
	 * password provided by the user.
	 * 
	 * @param username The username of the application user
	 * @param password The password of the user
	 * @return user    If the credentials are valid, it returns the User object.
	 */
	@Override
	public User login(String username, String password) {
		PreparedStatement statement = null;
		Connection connection = null;
		User user = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.LOGINUSERQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, username);
			statement.setString(2, password);
			resultset = statement.executeQuery();
			resultset.beforeFirst();
			while (resultset.next()) {
				user = new User();
				user.setUsername(resultset.getString(1));
				user.setName(resultset.getString(2));
				user.setMobile(resultset.getString(3));
				user.setEmail(resultset.getString(4));
				user.setCity(resultset.getString(5));
				user.setPassword(resultset.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return user;

	}

	/**
	 * 
	 * @param username    The username of the application user
	 * @param oldPassword The existing password of the user
	 * @param password    To be updated password
	 * @return Integer -  The update status
	 */
	@Override
	public int changePassword(String username, String oldPassword, String password) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		User user = login(username, oldPassword);
		if (user != null) {
			try {
				connection = DbConnection.openConnection();
				statement = connection.prepareStatement(Queries.PASSWORDUPDATEQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				statement.setString(2, username);
				statement.setString(1, password);
				result = statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DbConnection.closeConnection();
			}
		}
		return result;
	}

}