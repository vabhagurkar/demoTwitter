package com.social_ntw.display;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.social_ntw.domain.Tweet;
import com.social_ntw.utilities.TimeFormatter;

public class DisplayTweetsTest {
	private DisplayTweets displayTweets;
	private PrintStream printStream;
	private TimeFormatter timeFormatter;
	private ByteArrayOutputStream byteArrayOutStream;
	private static final String USER_1 = "Rita";

	@Before
	public void setUp() throws Exception {
		byteArrayOutStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutStream);
		timeFormatter = new TimeFormatter();
		displayTweets = new DisplayTweets(printStream, timeFormatter);
	}

	@Test
	public void testDisplayTweetsOnWall() throws UnsupportedEncodingException {
		String expectedResult = "Rita - I'm in Bradford today! Anyone want to have a coffee? (2 seconds ago)\r\n";
		Tweet tweet = new Tweet(USER_1, "I'm in Bradford today! Anyone want to have a coffee?",
				new Date(System.currentTimeMillis() - 2 * 1000L));

		assertNotNull(tweet);
		displayTweets.displayTweetsOnWall(tweet);
		assertEquals(expectedResult, byteArrayOutStream.toString("UTF-8"));
	}

	@Test
	public void testDisplayOwnTweets() throws UnsupportedEncodingException {
		String expectedResult = "I love the weather today (5 minutes ago)\r\n";
		Tweet tweet = new Tweet(USER_1, "I love the weather today",
				new Date(System.currentTimeMillis() - 5 * 60 * 1000L));
		assertNotNull(tweet);
		displayTweets.displayOwnTweets(tweet);
		assertEquals(expectedResult, byteArrayOutStream.toString("UTF-8"));
	}

	@Test
	public void testDisplayMessage() throws UnsupportedEncodingException {
		String message = "I love the weather today (5 minutes ago)";		
		displayTweets.displayMessage(message);
		assertEquals(message + "\r\n", byteArrayOutStream.toString("UTF-8"));
	}

}
