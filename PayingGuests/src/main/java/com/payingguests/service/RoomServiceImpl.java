package com.payingguests.service;

import java.util.List;
import java.util.stream.Collectors;

import com.payingguests.dao.IRoomDao;
import com.payingguests.dao.RoomDaoImpl;
import com.payingguests.exceptions.RoomNotFoundException;
import com.payingguests.model.Room;

public class RoomServiceImpl implements IRoomService {

	IRoomDao roomDao = new RoomDaoImpl();

	@Override
	public void addRoom(int payingGuestId, Room room) {
		roomDao.addRoom(payingGuestId, room);
	}

	@Override
	public int updateRoom(int roomId, String share) throws RoomNotFoundException {
		int result = roomDao.updateRoom(roomId, share);
		if (result == 0)
			throw new RoomNotFoundException("The room is not updated");
		return result;
	}

	@Override
	public int updateAvailability(boolean value, int roomId) throws RoomNotFoundException {
		int result = roomDao.updateAvailability(value, roomId);
		if (result == 0)
			throw new RoomNotFoundException("The availability is not updated");
		return result;
	}

	@Override
	public List<Room> getAllRooms() throws RoomNotFoundException {
		List<Room> rooms = roomDao.findAllRooms();
		if (rooms.isEmpty())
			throw new RoomNotFoundException("Rooms are not available");
		return rooms.stream().sorted((o1, o2) -> o1.getType().compareToIgnoreCase(o2.getType()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Room> getRoomsByType(String roomType) throws RoomNotFoundException {
		List<Room> rooms = roomDao.findRoomsByType(roomType);
		if (rooms.isEmpty())
			throw new RoomNotFoundException("Rooms of the given type are not available");

		return rooms.stream().sorted((o1, o2) -> o1.getType().compareToIgnoreCase(o2.getType()))
				.collect(Collectors.toList());

	}

	@Override
	public List<Room> getRoomsByPayingGuestType(String type) throws RoomNotFoundException {
		List<Room> rooms = roomDao.findRoomsbyPayingGuestType(type);
		if (rooms.isEmpty())
			throw new RoomNotFoundException("Rooms in the given Paying Guest are not available");

		return rooms.stream().sorted((o1, o2) -> o1.getType().compareToIgnoreCase(o2.getType()))
				.collect(Collectors.toList());

	}

	@Override
	public List<Room> getRoomByShare(String share) throws RoomNotFoundException {
		List<Room> rooms = roomDao.findRoombyShare(share);
		if (rooms.isEmpty())
			throw new RoomNotFoundException("Rooms in the given share type are not available");

		return rooms.stream().sorted((o1, o2) -> o1.getType().compareToIgnoreCase(o2.getType()))
				.collect(Collectors.toList());

	}

	@Override
	public List<Room> getRoomByPrice(double min, double max) throws RoomNotFoundException {
		List<Room> rooms = roomDao.findRoombyPrice(min, max);
		if (rooms.isEmpty())
			throw new RoomNotFoundException("Rooms in the given price range are not available");
		return rooms.stream().sorted((o1, o2) -> o1.getType().compareToIgnoreCase(o2.getType()))
				.collect(Collectors.toList());

	}

	@Override
	public int deleteRooms(int roomId) throws RoomNotFoundException {
		int result = roomDao.deleteRooms(roomId);
		if (result == 0)
			throw new RoomNotFoundException("Room cannot be deleted");
		return result;
	}

}
