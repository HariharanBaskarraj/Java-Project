package com.payingguests.main;

import java.util.Scanner;

import com.payingguests.model.User;
import com.payingguests.service.IUserService;
import com.payingguests.service.UserServiceImpl;

public class Login {

	/**
	 * This method is responsible for user operations like registering new user,
	 * validating existing users and changing the password.
	 * 
	 * @author HariharanB
	 * @return Nothing
	 */
	public void login() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		IUserService userService = new UserServiceImpl();
		User user = null;
		System.out.println(
				"Welcome to the Paying Guests Booking App\n1)Are you a new user? Register\n2)Existing user?? Login"
						+ "\n3)Change Password");
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

		case 3:
			System.out.println("Enter your username:");
			username = sc.nextLine();
			System.out.println("Enter your old password:");
			String oldPassword = sc.nextLine();
			System.out.println("Enter the new password:");
			password = sc.nextLine();
			userService.changePassword(username, oldPassword, password);
			break;

		default:
			System.out.println("Enter valid options!!!");
			System.exit(0);
		}
	}
}
