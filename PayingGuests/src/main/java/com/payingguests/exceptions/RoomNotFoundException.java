package com.payingguests.exceptions;

public class RoomNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RoomNotFoundException() {
		super();
	}

	public RoomNotFoundException(String message) {
		super(message);
	}
}
