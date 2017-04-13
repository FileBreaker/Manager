package com.filebreaker.manager.view.utils;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class TimeUtils {

	public static String getDuration(long durationMillis) {		
		PeriodFormatter formatter = new PeriodFormatterBuilder()
									        .printZeroIfSupported()
									        .appendHours()
									        .appendSeparator(":")
									        .minimumPrintedDigits(2)
									        .appendMinutes()
									        .appendSeparator(":")
									        .appendSecondsWithMillis()
									        .toFormatter();
		
		return formatter.print(new Period(durationMillis));
	}
}
