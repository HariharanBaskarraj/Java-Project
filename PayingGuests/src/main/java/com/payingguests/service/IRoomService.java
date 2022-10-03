package com.payingguests.service;

import java.util.List;

import com.payingguests.exceptions.RoomNotFoundException;
import com.payingguests.model.Room;

public interface IRoomService {

	void addRoom(int pgId, Room room);

	int updateRoom(int roomId, String share);

	int updateAvailability(boolean value,int roomId);

	List<Room> getAllRooms() throws RoomNotFoundException;

	List<Room> getRoomsByType(String roomType) throws RoomNotFoundException;

	List<Room> getRoomsByPayingGuestType(String type) throws RoomNotFoundException;

	List<Room> getRoomByShare(String share) throws RoomNotFoundException;

	List<Room> getRoomByPrice(double min, double max) throws RoomNotFoundException;

	int deleteRooms(int roomId);
}
