package com.social_ntw.inputCommand;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.social_ntw.domain.User;
import com.social_ntw.repository.UserRepository;

public class PostCommandTest {
	private PostCommand postCommand;
	private UserRepository userRepository = new UserRepository();
	
	private static final String USER_1 = "Rita";
	private static final String USER_2 = "Bob";
	
	private static final String USER_1_TWEET1 = "I love the weather today";
	private static final String USER_2_TWEET1 = "Damn! We lost!";
	private static final String USER_2_TWEET2 = "Good game though.";
	
	private static final String POST_COMMAND1 = USER_1 + " -> " + USER_1_TWEET1;
	private static final String POST_COMMAND2 = USER_2 + " -> " + USER_2_TWEET1;
	private static final String POST_COMMAND3 = USER_2 + " -> " + USER_2_TWEET2;
	
	@Before
	public void setUp() {
		postCommand = new PostCommand(userRepository);
	}
	
	@Test
	public void testValidCommand() {
		Assert.assertNotNull(postCommand);
		Assert.assertTrue(postCommand.validCommand((POST_COMMAND1)));
		Assert.assertTrue(postCommand.validCommand((POST_COMMAND2)));
		Assert.assertTrue(postCommand.validCommand((POST_COMMAND3)));
	}
	
	@Test
	public void testMatchNull() {
		assertFalse(postCommand.validCommand(null));
	}

	@Test
	public void testMatchEmptyl() {
		assertFalse(postCommand.validCommand(""));
	}

	@Test
	public void testRunPost() {
		User user1 = userRepository.createUser(USER_1);
		User user2 = userRepository.createUser(USER_2);
		
		assertNotNull(user1);
		assertNotNull(user2);
			
		postCommand.runCommand(POST_COMMAND1);
		assertNotNull(user1.getTweets());
		assertEquals(1, user1.getTweets().size());
		
		postCommand.runCommand(POST_COMMAND2);
		postCommand.runCommand(POST_COMMAND3);
		assertNotNull(user2.getTweets());
		assertEquals(2, user2.getTweets().size());		
	}
}
