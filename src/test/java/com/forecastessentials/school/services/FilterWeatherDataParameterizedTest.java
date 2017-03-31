package com.forecastessentials.school.services;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.forecastessentials.school.domain.DefaultSchoolTimings;
import com.forecastessentials.school.domain.SchoolTimings;

@RunWith(Parameterized.class)
/**
 * Use this Paramterized version instead of repeating the test class with
 * multiple methods...
 * 
 * @author Manglu
 *
 */
public class FilterWeatherDataParameterizedTest {

	boolean expectedValue;
	OffsetDateTime forecastDate;
	SchoolTimings schoolTimings;

	// Returns a formatter that works with dates in the format -
	// '2011-12-03T10:15:30+01:00'
	// private static DateTimeFormatter formatter =
	// DateTimeFormatter.ISO_OFFSET_DATE_TIME;

	final String dateTime = "2012-02-22T02:06:58.147Z";
	// Using the ISO_OFFSET_DATE_TIME resulted in a parseException...
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

	private static SchoolTimings defaultSchoolTimings = new DefaultSchoolTimings();

	public FilterWeatherDataParameterizedTest(boolean expectedValue, OffsetDateTime forecastDate,
			SchoolTimings schoolTimings) {

		this.expectedValue = expectedValue;
		this.forecastDate = forecastDate;
		this.schoolTimings = schoolTimings;
	}

	FilterWeatherData filter = null;

	@Before
	public void setup() {
		filter = new FilterWeatherData();
	}

	@Parameters
	public static List<Object[]> getParameters() {

		return Arrays.asList(new Object[][] {
				{ true, OffsetDateTime.parse("2017-03-09T12:00:00+1100", formatter), defaultSchoolTimings },
				{ true, OffsetDateTime.parse("2017-03-09T16:00:00+1100", formatter), defaultSchoolTimings },
				{ true, OffsetDateTime.parse("2017-03-09T08:00:00+1100", formatter), defaultSchoolTimings },
				{ false, OffsetDateTime.parse("2017-03-29T07:59:59+1100", formatter), defaultSchoolTimings },
				{ true, OffsetDateTime.parse("2017-03-29T16:00:01+1100", formatter), defaultSchoolTimings },
				{ true, OffsetDateTime.parse("2017-03-29T16:59:59+1100", formatter), defaultSchoolTimings },
				{ true, OffsetDateTime.parse("2017-03-09T16:00:00+1100", formatter), defaultSchoolTimings }

		});

	}

	@Test
	public void testIsThisDataWithinHoursOfInterest() {
		assertEquals(expectedValue, filter.isThisDataWithinHoursOfInterest(forecastDate, schoolTimings));

	}

}
