package com.social_ntw.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TweetTest {

	private Tweet tweet;
	@Test
	public void testTweet() {
		tweet = new Tweet("Sue", "I'm in Bradford today! Anyone want to have a coffee?", new Date());
		assertEquals("Sue", tweet.getUserName());
		assertEquals("I'm in Bradford today! Anyone want to have a coffee?", tweet.getMessage());
		assertNotNull(tweet.getTweetDate());
	}
}
