package com.social_ntw.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	private String name;
	private List<Tweet> tweets = new ArrayList<Tweet>();
	private List<User> followingUsers = new ArrayList<User>();

	public User(String name) {
		this.name = name;
	}

	public void postTweets(String message) {
		tweets.add(new Tweet(name, message, new Date()));
	}

	public void subscribeFollowing(User user) {
		if (!(followingUsers.contains(user))) {
			followingUsers.add(user);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<User> getFollowingUsers() {
		return followingUsers;
	}

	public void setFollowingUsers(List<User> followingUsers) {
		this.followingUsers = followingUsers;
	}

	// Two User objects are equal if their name are same
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final User other = (User) obj;

		if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        return result;
	}
}
