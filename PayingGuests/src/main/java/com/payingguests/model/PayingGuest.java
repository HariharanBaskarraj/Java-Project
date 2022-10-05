package com.payingguests.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payingguests.util.DbConnection;
import com.payingguests.util.PayingGuestMapper;
import com.payingguests.util.Queries;

public class PayingGuest {
	private Integer payingGuestId;
	private String payingGuestName;
	private String location;
	private String category;
	private List<Room> rooms;

	public PayingGuest() {
		super();
	}

	public PayingGuest(Integer payingGuestId, String payingGuestName, String location, String category,
			List<Room> rooms) {
		super();
		this.payingGuestId = payingGuestId;
		this.payingGuestName = payingGuestName;
		this.location = location;
		this.category = category;
		this.rooms = rooms;
	}

	public Integer getPayingGuestId() {
		return payingGuestId;
	}

	public void setPayingGuestId(Integer payingGuestId) {
		this.payingGuestId = payingGuestId;
	}

	public String getPayingGuestName() {
		return payingGuestName;
	}

	public void setPayingGuestName(String payingGuestName) {
		this.payingGuestName = payingGuestName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Room> getRooms() {
		PreparedStatement statement = null;
		Connection connection = null;
		rooms = new ArrayList<>();
		PayingGuestMapper mapper = null;
		try {
			ResultSet resultset = null;
			connection = DbConnection.openConnection();
			statement = connection.prepareStatement(Queries.APPENDROOMQUERY, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, this.payingGuestId);
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

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "PayingGuest [payingGuestId=" + payingGuestId + ", payingGuestName=" + payingGuestName + ", location="
				+ location + ", category=" + category + ", rooms=" + getRooms() + "]\n\n";
	}

}
