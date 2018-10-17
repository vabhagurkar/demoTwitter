package com.social_ntw.inputCommand;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.domain.Tweet;
import com.social_ntw.domain.User;
import com.social_ntw.repository.UserRepository;
import com.social_ntw.utilities.TimeFormatter;

public class WallCommandTest {
	private WallCommand wallCommand;
	private DisplayTweets displayTweets;
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrOutStream;
	private UserRepository userRepository = new UserRepository();
	
	private static final String USER_1 = "Sue";
	private static final String USER_2 = "Rita";		
	private static final String WALL_COMMAND = USER_1 + " wall";
	
	@Before
	public void setUp() {		
		byteArrOutStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrOutStream);
		displayTweets = new DisplayTweets(printStream, new TimeFormatter());
		wallCommand = new WallCommand(userRepository, displayTweets);
	}
	
	@Test
	public void testValidCommand() {
		Assert.assertNotNull(wallCommand);
		Assert.assertTrue(wallCommand.validCommand(WALL_COMMAND));
	}
	
	@Test
	public void testMatchNull() {
		assertFalse(wallCommand.validCommand(null));
	}

	@Test
	public void testMatchEmptyl() {
		assertFalse(wallCommand.validCommand(""));
	}

	@Test
	public void testRunWall() throws UnsupportedEncodingException {
		User user1 = userRepository.createUser(USER_1);
		assertNotNull(user1);
		user1.setTweets(Arrays.asList(new Tweet(USER_1, "I'm in Bradford today! Anyone want to have a coffee?", new Date(System.currentTimeMillis() - 2 * 1000L))));
		
		User user2 = userRepository.createUser(USER_2);
		assertNotNull(user2);
		user2.setTweets(Arrays.asList(new Tweet(USER_2, "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L))));
		
		user1.subscribeFollowing(user2);
		assertTrue(user1.getFollowingUsers().contains(user2));	
		
		wallCommand.runCommand(WALL_COMMAND);
		String expectedResult = "Sue - I'm in Bradford today! Anyone want to have a coffee? (2 seconds ago)\r\n"
				+ "Rita - I love the weather today (5 minutes ago)\r\n";
		assertEquals(expectedResult, byteArrOutStream.toString("UTF-8"));
	}
}
