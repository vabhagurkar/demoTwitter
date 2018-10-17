package com.social_ntw.inputCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.social_ntw.domain.User;
import com.social_ntw.repository.LocalMemoryUserRepository;

public class FollowCommand implements InputCommand {
	private static final Pattern FOLLOWS_PATTERN = Pattern.compile("^(\\S+) follows (\\S+)$");
	private LocalMemoryUserRepository userRepository;	
	
	public FollowCommand(LocalMemoryUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void runCommand(String command) {
		Matcher matcher = FOLLOWS_PATTERN.matcher(command);
		matcher.find();
		String userName =  matcher.group(1);
		String followingUserName =  matcher.group(2);
		
		User user = userRepository.getUser(userName);
		if(user == null) {
			user = userRepository.createUser(userName);
		}
		User followingUser = userRepository.getUser(followingUserName);
		if(followingUser == null) {
			followingUser = userRepository.createUser(followingUserName);
		}
		user.subscribeFollowing(followingUser);				
	}

	@Override
	public boolean validCommand(String command) {
		if(command != null) {
			Matcher matcher = FOLLOWS_PATTERN.matcher(command);
			return matcher.find();
		}
		return false;
	}

}
