package com.cybersolution.fazeal.logistics.util;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtill {

	public static long getUtcTime() {
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		return utc.toEpochSecond() * 1000;
	}
	
	public static String getDate() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return String.valueOf(dateFormat.format(new Date()));
	}

	/**
	 * Make an int Month from a date
	 */
	public static String getMonth() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return String.valueOf(dateFormat.format(new Date()));
	}

	/**
	 * Make an int Year from a date
	 */
	public static String getYear() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return String.valueOf(dateFormat.format(new Date()));
	}
}
