package com.payingguests.exceptions;

public class PayingGuestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PayingGuestNotFoundException() {
		super();
	}

	public PayingGuestNotFoundException(String message) {
		super(message);
	}

}
