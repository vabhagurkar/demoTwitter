package com.social_ntw.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.social_ntw.domain.User;

public class UserRepositoryTest {
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		userRepository = new UserRepository();
	}

	@Test
	public void testCreateUser() {
		User user = userRepository.createUser("Bob");
		assertNotNull(user);
		assertEquals("Bob", user.getName());
	}

	@Test
	public void testGetUser() {
		User user = userRepository.createUser("Bob");
		User newUser = userRepository.getUser("Bob");
		assertNotNull(user);
		assertNotNull(newUser);
		assertEquals(user, newUser);		
	}
}
