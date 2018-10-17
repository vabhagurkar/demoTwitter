package com.social_ntw.utilities;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeFormatter {
	private static final long ONE_MINUTE = TimeUnit.MINUTES.toMillis(1);
	private static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
	private static final long ONE_DAY = TimeUnit.DAYS.toMillis(1);

	public String formatDate(Date creationDate) {
		long timeDiff = System.currentTimeMillis() - creationDate.getTime();
		long displayTime;
		String timeUnit;
		if (timeDiff < ONE_MINUTE) {
			displayTime = TimeUnit.MILLISECONDS.toSeconds(timeDiff);
			timeUnit = "second";
		} else if (timeDiff < ONE_HOUR) {
			displayTime = TimeUnit.MILLISECONDS.toMinutes(timeDiff);
			timeUnit = "minute";
		} else if (timeDiff < ONE_DAY) {
			displayTime = TimeUnit.MILLISECONDS.toHours(timeDiff);
			timeUnit = "hour";
		} else {
			displayTime = TimeUnit.MILLISECONDS.toDays(timeDiff);
			timeUnit = "day";
		}
		if(displayTime > 1) {
			timeUnit += 's';
		}
		return String.format("(%d %s ago)", displayTime, timeUnit);
	}
}
