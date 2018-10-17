package com.social_ntw.repository;

import java.util.Map;
import java.util.TreeMap;

import com.social_ntw.domain.User;

public class UserRepository implements LocalMemoryUserRepository{
	private Map<String, User> userMap = new TreeMap<String, User>(String.CASE_INSENSITIVE_ORDER);

	@Override
	public User createUser(String name) {						
		userMap.putIfAbsent(name, new User(name));	
		return userMap.get(name);
	}

	@Override
	public User getUser(String name) {
		return userMap.get(name);
	}

}
