package com.payingguests.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.payingguests.exceptions.RoomNotFoundException;
import com.payingguests.util.DbConnection;
import com.payingguests.util.Queries;

public class BookDaoImpl implements IBookDao {
	IRoomDao roomDao = new RoomDaoImpl();

	/**
	 * This method is responsible for booking the room by the user.
	 * 
	 * @author HariharanB
	 * @param  roomId
	 * @return Boolean - The update status
	 * @throws RoomNotFoundException
	 */
	@Override
	public boolean bookRoom(int roomId) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		boolean result = false;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.SELECTAVAILABILITYQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, roomId);
			resultset = statement.executeQuery();
			resultset.next();
			result = resultset.getBoolean(1);
			if (result)
				roomDao.updateAvailability(false, roomId);

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
		return result;
	}

	/**
	 * This method is responsible for vacating the room by the user.
	 * 
	 * @author HariharanB
	 * @param  roomId
	 * @return Boolean - The update status
	 * @throws RoomNotFoundException
	 */
	@Override
	public boolean vacateRoom(int roomId) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		boolean result = false;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.SELECTAVAILABILITYQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, roomId);
			resultset = statement.executeQuery();
			result = resultset.next();
			result = resultset.getBoolean(1);
			if (!result)
				roomDao.updateAvailability(true, roomId);

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
		return result;
	}

}
