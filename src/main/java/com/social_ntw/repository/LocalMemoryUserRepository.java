package com.social_ntw.repository;

import com.social_ntw.domain.User;

public interface LocalMemoryUserRepository {

	public User createUser(String name);
	public User getUser(String name);
}
