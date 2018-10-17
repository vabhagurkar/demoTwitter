package com.social_ntw.inputCommand;

import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.social_ntw.display.DisplayTweets;
import com.social_ntw.repository.UserRepository;
import com.social_ntw.utilities.TimeFormatter;

public class CommandParserTest {
	private CommandParser commandParser;
	private static final String USER_1 = "Rita";
	private static final String USER_2 = "Bob";
	private static final String USER_3 = "Sue";
	
	private static final String USER_1_TWEET1 = "I love the weather today";
	private static final String USER_2_TWEET1 = "Damn! We lost!";
	private static final String USER_2_TWEET2 = "Good game though.";
	private static final String USER_3_TWEET1 = "I'm in Bradford today! Anyone want to have a coffee?";
	
	private static final String POST_COMMAND1 = USER_1 + " -> " + USER_1_TWEET1;
	private static final String POST_COMMAND2 = USER_2 + " -> " + USER_2_TWEET1;
	private static final String POST_COMMAND3 = USER_2 + " -> " + USER_2_TWEET2;
	private static final String POST_COMMAND4 = USER_3 + " -> " + USER_3_TWEET1;
	
	private static final String READ_COMMAND1 = USER_1;
	private static final String READ_COMMAND2 = USER_2;
	private static final String READ_COMMAND3 = USER_3;
	
	private static final String FOLLOW_COMMAND1 = USER_3 + " follows " + USER_1;
	private static final String FOLLOW_COMMAND2 = USER_3 + " follows " + USER_2;
	private static final String WALL_COMMAND1 = USER_3 + " wall";	
	
	PrintStream printStream;
	TimeFormatter timeFormatter;
	UserRepository userRepository = new UserRepository();
		
	@Before
	public void setUp() {
		DisplayTweets display = new DisplayTweets(printStream, timeFormatter);
		commandParser = new CommandParser(userRepository, display);
	}
	
	@Test
	public void testGetPostCommand() {
		InputCommand cmd1 = commandParser.getCommand(POST_COMMAND1);
		Assert.assertTrue(cmd1 instanceof PostCommand);
		InputCommand cmd2 = commandParser.getCommand(POST_COMMAND2);
		Assert.assertTrue(cmd2 instanceof PostCommand);
		InputCommand cmd3 = commandParser.getCommand(POST_COMMAND3);
		Assert.assertTrue(cmd3 instanceof PostCommand);
		InputCommand cmd4 = commandParser.getCommand(POST_COMMAND4);
		Assert.assertTrue(cmd4 instanceof PostCommand);
	}
	
	@Test
	public void testGetReadCommand() {
		InputCommand cmd1 = commandParser.getCommand(READ_COMMAND1);
		Assert.assertTrue(cmd1 instanceof ReadCommand);
		InputCommand cmd2 = commandParser.getCommand(READ_COMMAND2);
		Assert.assertTrue(cmd2 instanceof ReadCommand);
		InputCommand cmd3 = commandParser.getCommand(READ_COMMAND3);
		Assert.assertTrue(cmd3 instanceof ReadCommand);
	}
	
	@Test
	public void testGetFollowCommand() {
		InputCommand cmd1 = commandParser.getCommand(FOLLOW_COMMAND1);
		Assert.assertTrue(cmd1 instanceof FollowCommand);
		InputCommand cmd2 = commandParser.getCommand(FOLLOW_COMMAND2);
		Assert.assertTrue(cmd2 instanceof FollowCommand);
	}
	
	@Test
	public void testGetWallCommand() {
		InputCommand cmd1 = commandParser.getCommand(WALL_COMMAND1);
		Assert.assertTrue(cmd1 instanceof WallCommand);
	}	
}
