package com.social_ntw.inputCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.social_ntw.domain.User;
import com.social_ntw.repository.LocalMemoryUserRepository;

public class PostCommand implements InputCommand {

	private static final Pattern POST_REGEX = Pattern.compile("^(\\S+) -> (.+)$");
	private LocalMemoryUserRepository userRepository;
	
	public PostCommand(LocalMemoryUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void runCommand(String command) {
		Matcher matcher = POST_REGEX.matcher(command);
		matcher.find();
		String userName = matcher.group(1);
		String tweet = matcher.group(2);
			
		User user = userRepository.getUser(userName);
		if(user != null) {
			user.postTweets(tweet);
		}else {
			user = userRepository.createUser(userName);	
			user.postTweets(tweet);
		}
	}

	@Override
	public boolean validCommand(String command) {
		if(command != null) {			
			Matcher matcher = POST_REGEX.matcher(command);
			return matcher.find();
		}
		return false;
	}

}
