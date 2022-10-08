package com.payingguests.main;

import java.util.Scanner;

import com.payingguests.service.BookServiceImpl;
import com.payingguests.service.IBookService;

public class RoomOperations {
	
	/**
	 * This method is responsible for operations like booking and vacating a room.
	 * 
	 * @author HariharanB
	 * @return Nothing
	 */
	public void bookRoom() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		IBookService bookService = new BookServiceImpl();

		for (;;) {
			System.out.println("1) Book a room 2)Vacate a rooom");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the roomId of the room you want to book:");
				int roomId = sc.nextInt();
				sc.nextLine();
				boolean result = bookService.bookRoom(roomId);
				if (result)
					System.out.println("Booked the room");
				break;

			case 2:
				System.out.println("Enter the roomId of the room you want to vacate:");
				roomId = sc.nextInt();
				sc.nextLine();
				result = bookService.vacateRoom(roomId);
				if (!result)
					System.out.println("Vacated the room");
				break;

			default:
				System.out.println("Go with valid options");
			}
			System.out.println("Continue to menu? ??? Enter y");
			char status = Character.toUpperCase(sc.next().charAt(0));
			sc.nextLine();
			if (status != 'Y') {
				break;
			}
		}
	}
}
