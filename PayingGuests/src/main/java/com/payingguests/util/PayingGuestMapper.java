package com.payingguests.util;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payingguests.model.PayingGuest;
import com.payingguests.model.Room;

public class PayingGuestMapper implements RowMapper {

	@Override
	public List<PayingGuest> mapPayingGuestRow(ResultSet resultset) throws SQLException {
		List<PayingGuest> payingGuests = new ArrayList<>();
//		List<Room> roomsById=new ArrayList<>();
//		PreparedStatement statement = null;
//		Connection connection = null;
		PayingGuest payingGuest = null;
//		Room room = null;
/*		try {
			ResultSet resultsetAdd = null;
			connection = DbConnection.openConnection();*/
			resultset.beforeFirst();
			while (resultset.next()) {
				payingGuest = new PayingGuest();
				int payingGuestId = resultset.getInt(1);
				payingGuest.setPayingGuestId(payingGuestId);
				payingGuest.setPayingGuestName(resultset.getString(2));
				payingGuest.setLocation(resultset.getString(3));
				payingGuest.setCategory(resultset.getString(4));
				payingGuests.add(payingGuest);
	/*			statement = connection.prepareStatement(Queries.APPENDROOMQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				statement.setInt(1, payingGuestId);
				resultsetAdd = statement.executeQuery();
				resultsetAdd.beforeFirst();
				while (resultsetAdd.next()) {
					room = new Room();
					room.setRoomId(resultset.getInt(1));
					room.setType(resultset.getString(2));
					room.setShare(resultset.getString(3));
					room.setAvailability(resultset.getBoolean(4));
					room.setPrice(resultset.getDouble(5));
					roomsById.add(room);
					payingGuest.setRooms(roomsById);
				}
				payingGuests.add(payingGuest);
				System.out.println(payingGuest);
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
			DbConnection.closeConnection(); */
		} 
		return payingGuests;
	}

	@Override
	public List<Room> mapRoomRow(ResultSet resultset) throws SQLException {
		List<Room> rooms = new ArrayList<>();
		Room room = null;
		resultset.beforeFirst();
		while (resultset.next()) {
			room = new Room();
			room.setRoomId(resultset.getInt(1));
			room.setType(resultset.getString(2));
			room.setShare(resultset.getString(3));
			room.setAvailability(resultset.getBoolean(4));
			room.setPrice(resultset.getDouble(5));
			rooms.add(room);
		}
		return rooms;
	}
}
