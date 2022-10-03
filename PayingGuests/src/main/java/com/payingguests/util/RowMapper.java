package com.payingguests.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.payingguests.model.PayingGuest;
import com.payingguests.model.Room;

public interface RowMapper {
	
	List<PayingGuest> mapPayingGuestRow(ResultSet resultset) throws SQLException;

	List<Room> mapRoomRow(ResultSet resultset) throws SQLException;
	
}
