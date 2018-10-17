package com.social_ntw.inputCommand;

import com.social_ntw.display.DisplayTweets;

public class InvalidCommand implements InputCommand{

	private DisplayTweets displayTweets;
	
	public InvalidCommand(DisplayTweets displayTweets) {
		this.displayTweets = displayTweets;
	}

	@Override
	public void runCommand(String command) {
		displayTweets.displayMessage(String.format("Command: %s is invalid!", command));		
	}

	@Override
	public boolean validCommand(String command) {		
		return false;
	}
	
}
