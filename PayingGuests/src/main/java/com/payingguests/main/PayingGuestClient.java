package com.payingguests.main;

import java.util.List;
import java.util.Scanner;

import com.payingguests.model.Category;
import com.payingguests.model.PayingGuest;
import com.payingguests.model.Room;
import com.payingguests.model.Share;
import com.payingguests.model.Type;
import com.payingguests.model.User;
import com.payingguests.service.IPayingGuestService;
import com.payingguests.service.IRoomService;
import com.payingguests.service.IUserService;
import com.payingguests.service.PayingGuestServiceImpl;
import com.payingguests.service.RoomServiceImpl;
import com.payingguests.service.UserServiceImpl;

public class PayingGuestClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		IPayingGuestService payingGuestService = new PayingGuestServiceImpl();
		IRoomService roomService = new RoomServiceImpl();
		IUserService userService = new UserServiceImpl();
		PayingGuest payingGuest = null;
		Room room = null;
		User user = null;
		Thread.setDefaultUncaughtExceptionHandler((thread, e) -> System.out.println(e.getMessage()));

		System.out.println(
				"Welcome to the Paying Guests Booking App\n1)Are you a new user? Register\n2)Existing user?? Login");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			user = new User();
			System.out.println("Enter an username:");
			String username = sc.nextLine();
			user.setUsername(username);
			System.out.println("Enter your name:");
			String name = sc.nextLine();
			user.setName(name);
			System.out.println("Enter your mobile:");
			String mobile = sc.nextLine();
			user.setMobile(mobile);
			System.out.println("Enter your email:");
			String email = sc.nextLine();
			user.setEmail(email);
			System.out.println("Enter your city:");
			String city = sc.nextLine();
			user.setCity(city);
			String password = userService.register(user);
			System.out.println(password + " is your generated password. Use it to login.\nThank you...\n");

		case 2:
			System.out.println("Enter your username:");
			username = sc.nextLine();
			System.out.println("Enter your password:");
			password = sc.nextLine();
			userService.login(username, password);
			System.out.println("You are logged in successfully...\n");
			break;

		default:
			System.out.println("Enter valid options!!!");
			System.exit(0);
		}

		for (;;) {
			System.out.println("1)Add Paying Guest\n2)Add Room\n3)Update Share\n4)Update Availability\n"
					+ "5)Get all rooms\n6)Get rooms by type(AC/NONAC)\n7)Get rooms by category(BOYS/GIRLS/CO)\n"
					+ "8)Get rooms by Share\n9)Get rooms by Price\n10)Delete Rooms\n"
					+ "11)Update category of Paying Guest\n12)Get all Paying Guests\n13)Get Paying Guest by ID\n"
					+ "14)Get Paying Guest by Location\n15)Get Paying Guest by Category\n16)Delete Paying Guest");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Adding Paying Guests..... Enter the number of Paying Guests to add");
				int count = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < count; i++) {
					payingGuest = new PayingGuest();
					System.out.println("Enter the Paying Guest's Id:");
					int payingGuestId = sc.nextInt();
					sc.nextLine();
					payingGuest.setPayingGuestId(payingGuestId);
					System.out.println("Enter the Paying Guest's Name:");
					String payingGuestName = sc.nextLine();
					payingGuest.setPayingGuestName(payingGuestName);
					System.out.println("Enter the Paying Guest's location:");
					String payingGuestLocation = sc.nextLine();
					payingGuest.setLocation(payingGuestLocation);
					System.out.println("Enter the Paying Guest's category");
					String payingGuestCategory = sc.nextLine();
					payingGuest.setCategory(Category.valueOf(payingGuestCategory).category);
					payingGuestService.addPayingGuest(payingGuest);
				}
				break;

			case 2:
				System.out.println("Adding rooms..... Enter the number of rooms to add");
				count = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < count; i++) {
					room = new Room();
					System.out.println("Enter the Paying Guest's Id:");
					int payingGuestId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the room id:");
					int roomId = sc.nextInt();
					sc.nextLine();
					room.setRoomId(roomId);
					System.out.println("Enter the type (AC/NONAC):");
					String roomType = sc.nextLine();
					room.setType(Type.valueOf(roomType).type);
					System.out.println("Enter the share (BOYS/GIRLS/CO):");
					String roomShare = sc.nextLine();
					room.setShare(Share.valueOf(roomShare).share);
					System.out.println("Enter the price:");
					double roomPrice = sc.nextDouble();
					sc.nextLine();
					room.setPrice(roomPrice);
					roomService.addRoom(payingGuestId, room);
				}
				break;

			case 3:
				System.out.println("Updating the share type.....\nEnter the room Id:");
				int roomId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the share type:(ONE/TWO/THREE)");
				String roomShare = sc.nextLine();
				roomService.updateRoom(roomId, roomShare);
				break;

			case 4:
				System.out.println("Updating the availability.....\nEnter the room Id:");
				sc.nextLine();
				roomId = sc.nextInt();
				System.out.println("Enter the availability:(true/false)");
				boolean value = sc.nextBoolean();
				sc.nextLine();
				roomService.updateAvailability(value, roomId);
				break;

			case 5:
				System.out.println("Getting all the rooms.....");
				List<Room> rooms = roomService.getAllRooms();
				System.out.println(rooms);
				break;

			case 6:
				System.out.println("Getting rooms by Type.....");
				System.out.println("Enter the Type(AC/NONAC):");
				sc.nextLine();
				roomShare = sc.nextLine();
				rooms = roomService.getRoomsByType(roomShare);
				System.out.println(rooms);
				break;

			case 7:
				System.out.println("Getting rooms by Category.....");
				System.out.println("Enter the Category type(BOYS/GIRLS/CO):");
				sc.nextLine();
				String category = sc.nextLine();
				rooms = roomService.getRoomsByPayingGuestType(category);
				System.out.println(rooms);
				break;

			case 8:
				System.out.println("Getting rooms by Share.....");
				System.out.println("Enter the Share type(ONE/TWO/THREE):");
				sc.nextLine();
				roomShare = sc.nextLine();
				rooms = roomService.getRoomByShare(roomShare);
				System.out.println(rooms);
				break;

			case 9:
				System.out.println("Getting rooms by Price.....");
				System.out.println("Enter the minimum amount:");
				double min = sc.nextDouble();
				System.out.println("Enter the maximum amount:");
				double max = sc.nextDouble();
				sc.nextLine();
				rooms = roomService.getRoomByPrice(min, max);
				System.out.println(rooms);
				break;

			case 10:
				System.out.println("Deleting the rooms.....\nEnter the number of rooms you want to delete:");
				count = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < count; i++) {
					System.out.println("Enter the room's Id:");
					roomId = sc.nextInt();
					sc.nextLine();
					roomService.deleteRooms(roomId);
					System.out.println("Room is deleted!!!");
				}
				break;

			case 11:
				System.out.println("Updating the category.....\nEnter the Paying Guest's Id:");
				int payingGuestId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the category(BOYS/GIRLS/CO)");
				category = sc.nextLine();
				payingGuestService.updatePayingGuest(payingGuestId, category);
				break;

			case 12:
				System.out.println("Getting all the Paying Guests.....");
				List<PayingGuest> payingGuests = payingGuestService.getAllPayingGuest();
				System.out.println(payingGuests);
				break;

			case 13:
				System.out.println("Getting Paying Guests by ID.....\nEnter the Paying Guest's Id:");
				payingGuestId = sc.nextInt();
				sc.nextLine();
				payingGuest = payingGuestService.getPayingGuestById(payingGuestId);
				System.out.println(payingGuest);
				break;

			case 14:
				System.out.println("Getting Paying Guests by location.....\nEnter the location:");
				sc.nextLine();
				String location = sc.nextLine();
				payingGuests = payingGuestService.getPayingGuestByLocation(location);
				System.out.println(payingGuests);
				break;

			case 15:
				System.out.println("Getting Paying Guests by category.....\nEnter the category(BOYS/GIRLS/CO):");
				sc.nextLine();
				category = sc.nextLine();
				payingGuests = payingGuestService.getPayingGuestByCategory(category);
				System.out.println(payingGuests);
				break;

			case 16:
				System.out.println(
						"Deleting the Paying Guest.....\nEnter the number of Paying Guests you want to delete: ");
				count = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < count; i++) {
					System.out.println("Enter the Paying Guest's Id:");
					payingGuestId = sc.nextInt();
					sc.nextLine();
					payingGuestService.deletePayingGuest(payingGuestId);
					System.out.println("Paying Guest is deleted!!!");
				}
				break;

			default:
				System.out.println("Go with valid options!!!");
			}

			System.out.println("Continue??? Enter y");
			char status = Character.toUpperCase(sc.next().charAt(0));
			sc.nextLine();
			if (status != 'Y') {
				sc.close();
				System.exit(0);
			}
		}

	}

}
