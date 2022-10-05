package com.payingguests.dao;

import com.payingguests.exceptions.RoomNotFoundException;

public interface IBookDao {
	public boolean bookRoom(int roomId) throws RoomNotFoundException;

	public boolean vacateRoom(int roomId) throws RoomNotFoundException;

}
