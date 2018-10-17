package com.social_ntw.inputCommand;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.utilities.TimeFormatter;

public class InvalidCommandTest {
	private InvalidCommand invalidCommand;
	private DisplayTweets displayTweets;
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrOutStream;
	
	private static final String INVALID_COMMAND = "Sue xyz";
	
	@Before
	public void setUp() {		
		byteArrOutStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrOutStream);
		displayTweets = new DisplayTweets(printStream, new TimeFormatter());
		invalidCommand = new InvalidCommand(displayTweets);
	}
	
	@Test
	public void testValidCommand() {
		Assert.assertNotNull(invalidCommand);
		Assert.assertFalse(invalidCommand.validCommand(INVALID_COMMAND));
	}

	@Test
	public void testRunInvalid() throws UnsupportedEncodingException {
		invalidCommand.runCommand(INVALID_COMMAND);
		String expectedResult = "Command: " + INVALID_COMMAND + " is invalid!\r\n";
		assertEquals(expectedResult, byteArrOutStream.toString("UTF-8"));
	}
}
