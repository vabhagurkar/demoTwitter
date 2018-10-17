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

public class ReadCommandTest {
	private ReadCommand readCommand;
	private DisplayTweets displayTweets;
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrOutStream;
	private UserRepository userRepository = new UserRepository();
	
	private static final String USER = "Rita";		
	private static final String READ_COMMAND = USER;
	
	@Before
	public void setUp() {		
		byteArrOutStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrOutStream);
		displayTweets = new DisplayTweets(printStream, new TimeFormatter());
		readCommand = new ReadCommand(userRepository, displayTweets);
	}
	
	@Test
	public void testValidCommand() {
		Assert.assertNotNull(readCommand);
		Assert.assertTrue(readCommand.validCommand((READ_COMMAND)));
	}
	
	@Test
	public void testMatchNull() {
		assertFalse(readCommand.validCommand(null));
	}

	@Test
	public void testMatchEmptyl() {
		assertFalse(readCommand.validCommand(""));
	}

	@Test
	public void testRunRead() throws UnsupportedEncodingException {
		User user = userRepository.createUser(USER);
		assertNotNull(user);
		user.setTweets(Arrays.asList(new Tweet(USER, "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L))));
		readCommand.runCommand(READ_COMMAND);
		String expectedResult = "I love the weather today (5 minutes ago)" + "\r\n";				
		assertEquals(expectedResult, byteArrOutStream.toString("UTF-8"));
	}
}