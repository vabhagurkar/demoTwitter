package com.social_ntw.inputCommand;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.social_ntw.domain.Tweet;
import com.social_ntw.domain.User;
import com.social_ntw.repository.UserRepository;

public class FollowCommandTest {
	private FollowCommand followCommand;	
	private UserRepository userRepository = new UserRepository();
	
	private static final String USER_1 = "Sue";
	private static final String USER_2 = "Rita";	
	private static final String FOLLOW_COMMAND = USER_1 + " follows " + USER_2;
	
	@Before
	public void setUp() {				
		followCommand = new FollowCommand(userRepository);
	}
	
	@Test
	public void testValidCommand() {
		Assert.assertNotNull(followCommand);
		Assert.assertTrue(followCommand.validCommand((FOLLOW_COMMAND)));
	}
	
	@Test
	public void testMatchNull() {
		assertFalse(followCommand.validCommand(null));
	}

	@Test
	public void testMatchEmptyl() {
		assertFalse(followCommand.validCommand(""));
	}

	@Test
	public void testRunFollow() {
		User user1 = userRepository.createUser(USER_1);
		assertNotNull(user1);
		user1.setTweets(Arrays.asList(new Tweet(USER_1, "I'm in Bradford today! Anyone want to have a coffee?", new Date(System.currentTimeMillis()))));
		
		User user2 = userRepository.createUser(USER_2);
		assertNotNull(user2);
		user2.setTweets(Arrays.asList(new Tweet(USER_2, "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L))));
		
		followCommand.runCommand(FOLLOW_COMMAND);
		assertTrue(user1.getFollowingUsers().contains(user2));		
	}
}