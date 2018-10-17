package com.social_ntw.inputCommand;

import java.util.ArrayList;
import java.util.List;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.repository.LocalMemoryUserRepository;

public class CommandParser {
	
	private List<InputCommand> commandList = new ArrayList<>();
	InvalidCommand invalidCommand;
	LocalMemoryUserRepository userRepository;
	
	public CommandParser(LocalMemoryUserRepository userRepository2, DisplayTweets displayTweets) {
		commandList.add(new PostCommand(userRepository2));
		commandList.add(new FollowCommand(userRepository2));
		commandList.add(new WallCommand(userRepository2, displayTweets));
		commandList.add(new ReadCommand(userRepository2, displayTweets));	
		invalidCommand = new InvalidCommand(displayTweets); 
	}
	
	public void executeCommand(String command) {
		InputCommand inputCommand = getCommand(command);
		inputCommand.runCommand(command);
	}

	public InputCommand getCommand(String command) {
		for(InputCommand cmd : commandList) {
			if(cmd.validCommand(command)) {
				return cmd;
			}
		}
		return invalidCommand;
	}

}
