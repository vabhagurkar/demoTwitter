package com.social_ntw.display;

import java.io.PrintStream;

import com.social_ntw.domain.Tweet;
import com.social_ntw.utilities.TimeFormatter;

public class DisplayTweets implements Display{
	
	private static final String MESSAGE_FORMAT_FOR_WALL = "%s - %s %s";
	private static final String MESSAGE_FORMAT_OWN = "%s %s";
	PrintStream printStream;
	TimeFormatter timeFormatter;

	public DisplayTweets(PrintStream printStream, TimeFormatter timeFormatter) {
		this.printStream = printStream;
		this.timeFormatter = timeFormatter;
	}
	
	@Override
	public void displayTweetsOnWall(Tweet tweet) {
		printStream.println(String.format(MESSAGE_FORMAT_FOR_WALL, tweet.getUserName(), tweet.getMessage(), timeFormatter.formatDate((tweet.getTweetDate()))));
	}

	@Override
	public void displayOwnTweets(Tweet tweet) {		
		printStream.println(String.format(MESSAGE_FORMAT_OWN, tweet.getMessage(), timeFormatter.formatDate((tweet.getTweetDate()))));
	}

	@Override
	public void displayMessage(String message) {
		printStream.println(message);		
	}

}
