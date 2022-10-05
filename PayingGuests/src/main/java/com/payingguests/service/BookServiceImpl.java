package com.payingguests.service;

import com.payingguests.dao.BookDaoImpl;
import com.payingguests.dao.IBookDao;
import com.payingguests.exceptions.RoomNotFoundException;

/**
 * @author HariharanB
 *
 */
public class BookServiceImpl implements IBookService {

	IBookDao bookDao =new BookDaoImpl();
	/**
	 * @param roomId
	 * @throws RoomNotFoundException
	 */
	@Override
	public boolean bookRoom(int roomId) throws RoomNotFoundException {
		boolean result = bookDao.bookRoom(roomId);
		if (!result)
			throw new RoomNotFoundException("Room is already occupied. Go with other rooms");
		return result;
	}

	/**
	 * @param roomId
	 * @throws RoomNotFoundException
	 */
	@Override
	public boolean vacateRoom(int roomId) throws RoomNotFoundException {
		boolean result = bookDao.vacateRoom(roomId);
		if (result)
			throw new RoomNotFoundException("Room is empty. Check your room Id");
		return result;
	}

}
