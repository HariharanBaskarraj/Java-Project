package com.payingguests.dao;

import java.util.List;

import com.payingguests.exceptions.RoomNotFoundException;
import com.payingguests.model.Room;

public interface IRoomDao {
	
	void addRoom(int payingGuestId, Room room);

	int updateRoom(int roomId, String share);

	int updateAvailability(boolean value,int roomId);

	List<Room> findAllRooms() throws RoomNotFoundException;

	List<Room> findRoomsByType(String roomType) throws RoomNotFoundException;

	List<Room> findRoomsbyPayingGuestType(String type) throws RoomNotFoundException;

	List<Room> findRoombyShare(String share) throws RoomNotFoundException;

	List<Room> findRoombyPrice(double min, double max) throws RoomNotFoundException;

	int deleteRooms(int roomId)  ;
}
