package com.social_ntw.inputCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.domain.Tweet;
import com.social_ntw.domain.User;
import com.social_ntw.repository.LocalMemoryUserRepository;

public class WallCommand implements InputCommand {

	private static final Pattern WALL_REGEX = Pattern.compile("^(\\S+) wall$");
	private LocalMemoryUserRepository userRepository;
	private DisplayTweets displayTweets;

	public WallCommand(LocalMemoryUserRepository userRepository, DisplayTweets displayTweets) {
		this.userRepository = userRepository;
		this.displayTweets = displayTweets;
	}

	@Override
	public void runCommand(String command) {		
		Matcher matcher = WALL_REGEX.matcher(command);
		matcher.find();
		String userName = matcher.group(1);
		User user = userRepository.getUser(userName);		
		if(user == null) {
		     user = userRepository.createUser(userName);
		}
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.addAll(user.getTweets());		
		
		for(User followUser : user.getFollowingUsers()) {
			tweetList.addAll(followUser.getTweets());
		}
		
		//Overriding Comparator
		Collections.sort(tweetList,((Tweet t1, Tweet t2) -> t2.getTweetDate().compareTo(t1.getTweetDate())));			
		tweetList.forEach(e -> displayTweets.displayTweetsOnWall(e));
	}

	@Override
	public boolean validCommand(String command) {
		if (command != null) {
			Matcher matcher = WALL_REGEX.matcher(command);
			return matcher.find();
		}
		return false;
	}

}
