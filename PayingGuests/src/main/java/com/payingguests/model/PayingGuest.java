package com.payingguests.model;

import java.util.List;

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
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "PayingGuest [payingGuestId=" + payingGuestId + ", payingGuestName=" + payingGuestName + ", location="
				+ location + ", category=" + category + ", rooms=" + rooms + "]\n";
	}

}
