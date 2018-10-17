package com.social_ntw.display;

import com.social_ntw.domain.Tweet;

public interface Display {

	public void displayTweetsOnWall(Tweet tweet);
	public void displayOwnTweets(Tweet tweet);
	public void displayMessage(String message); 
}
