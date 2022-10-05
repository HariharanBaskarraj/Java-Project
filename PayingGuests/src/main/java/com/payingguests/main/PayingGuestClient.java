package com.payingguests.main;

public class PayingGuestClient {

	public static void main(String[] args) {		
		Thread.setDefaultUncaughtExceptionHandler((thread, e) -> System.out.println(e.getMessage()));
		
		Login login = new Login();
		login.login();
		
		UserMenu userMenu = new UserMenu();
		userMenu.menu();
		
		RoomOperations roomOperations = new RoomOperations();
		roomOperations.bookRoom();

	}

}
