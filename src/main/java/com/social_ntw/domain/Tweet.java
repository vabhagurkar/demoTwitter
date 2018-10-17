package com.social_ntw.domain;

import java.util.Date;

public class Tweet {
	private String userName;
	private String message;
	private Date tweetDate;
	
	public Tweet(String userName, String message) {
		this.userName = userName;
		this.message = message;
	}
	
	public Tweet(String userName, String message, Date tweetDate) {
		this.userName = userName;
		this.message = message;
		this.tweetDate = tweetDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTweetDate() {
		return tweetDate;
	}

	public void setTweetDate(Date tweetDate) {
		this.tweetDate = tweetDate;
	}
}
