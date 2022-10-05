package com.payingguests.model;

public class Room {
	private int roomId;
	private String type;
	private String share;
	private boolean availability;
	private double price;

	public Room() {
		super();
	}

	public Room(int roomId, String type, String share, boolean availability, double price) {
		super();
		this.roomId = roomId;
		this.type = type;
		this.share = share;
		this.availability = availability;
		this.price = price;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", type=" + type + ", share=" + share + ", availability=" + availability
				+ ", price=" + price + "]\n";
	}

}
