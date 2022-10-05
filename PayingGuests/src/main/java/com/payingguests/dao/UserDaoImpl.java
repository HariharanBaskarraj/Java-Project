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