package com.social_ntw.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.social_ntw.display.DisplayTweetsTest;
import com.social_ntw.domain.TweetTest;
import com.social_ntw.domain.UserTest;
import com.social_ntw.inputCommand.CommandParserTest;
import com.social_ntw.inputCommand.FollowCommandTest;
import com.social_ntw.inputCommand.InvalidCommandTest;
import com.social_ntw.inputCommand.PostCommandTest;
import com.social_ntw.inputCommand.ReadCommandTest;
import com.social_ntw.inputCommand.WallCommandTest;
import com.social_ntw.repository.UserRepositoryTest;
import com.social_ntw.utilities.TimeFormatterTest;

//Unit Test for Simple App.
@RunWith(Suite.class)
@SuiteClasses({CommandParserTest.class, PostCommandTest.class, ReadCommandTest.class, FollowCommandTest.class, WallCommandTest.class,
	InvalidCommandTest.class, UserTest.class, TweetTest.class, UserRepositoryTest.class, TimeFormatterTest.class, DisplayTweetsTest.class})
public class AppTest {

}