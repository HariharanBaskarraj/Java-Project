package com.payingguests.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payingguests.exceptions.RoomNotFoundException;
import com.payingguests.model.Room;
import com.payingguests.model.Share;
import com.payingguests.model.Type;
import com.payingguests.model.Category;
import com.payingguests.util.DbConnection;
import com.payingguests.util.PayingGuestMapper;
import com.payingguests.util.Queries;

public class RoomDaoImpl implements IRoomDao {

	/**
	 * This method parses the room object and adds it to the Room Table.
	 * 
	 * @author HariharanB
	 * @param payingGuestId The Paying Guest ID for the reference to the PayingGuest
	 *                      table
	 * @param room          Room object to add the room fields to the table
	 * @return Nothing
	 */
	@Override
	public void addRoom(int payingGuestId, Room room) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.INSERTROOMQUERY);
			statement.setInt(1, room.getRoomId());
			statement.setString(2, room.getType());
			statement.setString(3, room.getShare());
			statement.setBoolean(4, true);
			statement.setDouble(5, room.getPrice());
			statement.setInt(6, payingGuestId);
			System.out.println("Room added");
			statement.execute();
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

	/**
	 * This method updates the share type of the referenced room (with Room ID).
	 * 
	 * @author HariharanB
	 * @param roomId The Room ID for updating
	 * @param share  The share type that needs to be updated
	 * @see Room table in the PGAppDB database
	 * @return Integer - Update status
	 */
	@Override
	public int updateRoom(int roomId, String share) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.UPDATEROOMQUERY);
			statement.setString(1, Share.valueOf(share).share);
			statement.setInt(2, roomId);
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
		return result;
	}

	/**
	 * This method sets the availability to true/false based on the occupancy
	 * status.
	 * 
	 * @author HariharanB
	 * @param value True/False based on the occupancy status.
	 * @param roomId The Room ID for updating
	 * @return Integer - Update status
	 */
	@Override
	public int updateAvailability(boolean value, int roomId) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.UPDATEAVAILABILITYQUERY);
			statement.setBoolean(1, value);
			statement.setInt(2, roomId);
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
		return result;
	}

	/**
	 * This method is responsible for retrieving all the Room records from the table
	 * 
	 * @author HariharanB
	 * @return The list containing all the room objects
	 */
	@Override
	public List<Room> findAllRooms() throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		PayingGuestMapper mapper = null;
		List<Room> rooms = new ArrayList<>();
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.SELECTROOMQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			rooms = mapper.mapRoomRow(resultset);
			resultset = statement.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	/**
	 * This method is responsible for retrieving the room records that match the
	 * given room type.
	 * 
	 * @author HariharanB
	 * @param roomType To search for the rooms with this matching type
	 * @return The list containing the room objects that match user given room type
	 */
	@Override
	public List<Room> findRoomsByType(String roomType) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<Room> rooms = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYROOMTYPE, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, Type.valueOf(roomType).type);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			rooms = mapper.mapRoomRow(resultset);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	/**
	 * This method is responsible for retrieving the room records that match the
	 * given Paying Guest type.
	 * 
	 * @author HariharanB
	 * @param type To search for the rooms with this matching Paying Guest type
	 * @return The list containing the room objects that match user given Paying
	 *         Guest type
	 */
	@Override
	public List<Room> findRoomsbyPayingGuestType(String type) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<Room> rooms = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYPGTYPE, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, Category.valueOf(type).category);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			rooms = mapper.mapRoomRow(resultset);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	/**
	 * This method is responsible for retrieving the room records that match the
	 * given share type.
	 * 
	 * @author HariharanB
	 * @param share To search for the rooms with this matching share type
	 * @return The list containing the room objects that match user given share type
	 */
	@Override
	public List<Room> findRoombyShare(String share) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<Room> rooms = new ArrayList<>();
		PayingGuestMapper mapper = null;

		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.QUERYBYSHARE, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, Share.valueOf(share).share);
			resultset = statement.executeQuery();
			mapper = new PayingGuestMapper();
			rooms = mapper.mapRoomRow(resultset);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	/**
	 * This method is responsible for retrieving the room records that is in the
	 * range of the minimum and maximum value that is given by the user.
	 * 
	 * @author HariharanB
	 * @param roomType To search for the rooms with this matching type
	 * @return The list containing the room objects that match user given room type
	 */
	@Override
	public List<Room> findRoombyPrice(double min, double max) throws RoomNotFoundException {
		PreparedStatement statement = null;
		Connection connection = null;
		List<Room> rooms = new ArrayList<>();

		Room room = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.SELECTROOMQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery();
			resultset.beforeFirst();
			while (resultset.next()) {
				double price = resultset.getDouble(5);
				if (price >= min && price <= max) {
					room = new Room();
					room.setRoomId(resultset.getInt(1));
					room.setType(resultset.getString(2));
					room.setShare(resultset.getString(3));
					room.setAvailability(resultset.getBoolean(4));
					room.setPrice(price);
					rooms.add(room);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	/**
	 * This method is responsible for deleting the referenced room (with Room ID).
	 * 
	 * @author HariharanB
	 * @param roomId The Room ID for deleting
	 * @return Integer - Update status
	 */
	@Override
	public int deleteRooms(int roomId) {
		PreparedStatement statement = null;
		Connection connection = null;
		int result = 0;
		try {
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.DELETEROOMQUERY);
			statement.setInt(1, roomId);
			result = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
