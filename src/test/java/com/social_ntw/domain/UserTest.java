package com.social_ntw.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User("Rita");
	}

	@Test
	public void testPostTweets() {
		user.postTweets("I love the weather today");
		assertEquals("Rita", user.getTweets().get(0).getUserName());
		assertEquals(1, user.getTweets().size());
		assertEquals("I love the weather today", user.getTweets().get(0).getMessage());
	}

	@Test
	public void testSubscribeFollowing() {
		user.subscribeFollowing(new User("Bob"));
		assertEquals("Bob", user.getFollowingUsers().get(0).getName());
		assertEquals(1, user.getFollowingUsers().size());
	}

	@Test
	public void testDuplicateSubscribing() {
		user.subscribeFollowing(new User("Bob"));
		user.subscribeFollowing(new User("Bob"));
		assertEquals("Bob", user.getFollowingUsers().get(0).getName());
		assertEquals(1, user.getFollowingUsers().size());		
	}

	@Test
	public void testSetFollowingUsers() {
		List<User> followUser = new ArrayList<User>();
		followUser.add(new User("Bob"));
		user.setFollowingUsers(followUser);
		assertEquals(1, user.getFollowingUsers().size());
	}	
}
