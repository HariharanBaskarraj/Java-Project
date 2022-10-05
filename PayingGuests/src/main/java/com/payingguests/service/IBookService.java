/**
 * 
 */
package com.payingguests.service;

import com.payingguests.exceptions.RoomNotFoundException;

/**
 * @author HariharanB
 *
 */
public interface IBookService {
	public boolean bookRoom(int roomId) throws RoomNotFoundException;

	public boolean vacateRoom(int roomId) throws RoomNotFoundException;
}
