package com.social_ntw.app;

import java.util.Scanner;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.inputCommand.CommandParser;
import com.social_ntw.repository.LocalMemoryUserRepository;
import com.social_ntw.repository.UserRepository;
import com.social_ntw.utilities.TimeFormatter;

public class App {

	private static Scanner scanner = new Scanner(System.in);
//
	public static void main(String[] args) {
		LocalMemoryUserRepository userRepository = new UserRepository();
		DisplayTweets displayTweets = new DisplayTweets(System.out, new TimeFormatter());
		CommandParser commandParser = new CommandParser(userRepository, displayTweets);
		String message = "";
		try {
			while (!message.matches("(?i:.*Exit.*)")) {
				System.out.print(AppConstants.CHAR_FIRST_CHAR);
				message = scanner.nextLine();
				commandParser.executeCommand(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
		System.exit(0);
	}
}