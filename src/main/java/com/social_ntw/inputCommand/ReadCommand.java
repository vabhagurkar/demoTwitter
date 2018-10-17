package com.social_ntw.inputCommand;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.domain.Tweet;
import com.social_ntw.domain.User;
import com.social_ntw.repository.LocalMemoryUserRepository;

public class ReadCommand implements InputCommand {

	private static final Pattern READ_REGEX = Pattern.compile("^(\\S+)$");
	private LocalMemoryUserRepository userRepository;
	private DisplayTweets displayTweets;

	public ReadCommand(LocalMemoryUserRepository userRepository, DisplayTweets displayTweets) {
		this.userRepository = userRepository;
		this.displayTweets = displayTweets;
	}

	@Override
	public void runCommand(String command) {
		Matcher matcher = READ_REGEX.matcher(command.trim());
		matcher.find();
		
		String userName = matcher.group(1);
		User user = userRepository.getUser(userName);
		if (user == null) {
			user = userRepository.createUser(userName);
		}
		List<Tweet> tweetList = user.getTweets();
		
		// Overriding Comparator
		if (!tweetList.isEmpty()) {			
			Collections.sort(tweetList, ((Tweet t1, Tweet t2) -> t2.getTweetDate().compareTo(t1.getTweetDate())));
			for(Tweet t : tweetList) {
				displayTweets.displayOwnTweets(t);
			}
		}
	}

	@Override
	public boolean validCommand(String command) {
		if (command != null) {
			Matcher matcher = READ_REGEX.matcher(command);
			return matcher.find();
		}
		return false;
	}

}
