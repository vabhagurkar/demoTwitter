package com.social_ntw.utilities;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TimeFormatterTest {
	private TimeFormatter timeFormatter;

	@Before
	public void setup() {
		timeFormatter = new TimeFormatter();
	}
	
	@Test
	public void testFormatDate() {
		Date date = new Date();
		String result = timeFormatter.formatDate(date);
		assertEquals("(0 second ago)", result);
	}
	
	@Test
	public void testFormatDateSeconds() {
		Date date = new Date(System.currentTimeMillis() - 5 * 1000L);
		String result = timeFormatter.formatDate(date);
		assertEquals("(5 seconds ago)", result);
	}
	
	@Test
	public void testFormatDateMinutes() {
		Date date = new Date(System.currentTimeMillis() - 10 * 60 * 1000L);
		String result = timeFormatter.formatDate(date);
		assertEquals("(10 minutes ago)", result);
	}
	
	@Test
	public void testFormatDateHours() {
		Date date = new Date(System.currentTimeMillis() - 8 * 60 * 60 * 1000L);
		String result = timeFormatter.formatDate(date);
		assertEquals("(8 hours ago)", result);
	}
	
	@Test
	public void testFormatDateDays() {
		Date date = new Date(System.currentTimeMillis() - 73 * 60 * 60 * 1000L);
		String result = timeFormatter.formatDate(date);
		assertEquals("(3 days ago)", result);
	}

}
