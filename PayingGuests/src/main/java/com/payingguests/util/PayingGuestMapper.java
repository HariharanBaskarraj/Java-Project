package com.payingguests.util;

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

		PayingGuest payingGuest = null;

			resultset.beforeFirst();
			while (resultset.next()) {
				payingGuest = new PayingGuest();
				int payingGuestId = resultset.getInt(1);
				payingGuest.setPayingGuestId(payingGuestId);
				payingGuest.setPayingGuestName(resultset.getString(2));
				payingGuest.setLocation(resultset.getString(3));
				payingGuest.setCategory(resultset.getString(4));
				payingGuests.add(payingGuest);
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
