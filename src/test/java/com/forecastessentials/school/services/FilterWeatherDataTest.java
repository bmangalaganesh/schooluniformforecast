package com.forecastessentials.school.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.forecastessentials.school.domain.DefaultSchoolTimings;
import com.forecastessentials.school.domain.SchoolTimings;

@Ignore
/**
 * Ignore this test as I have replaced this with Parameterized Test
 * @author Manglu
 *
 */
public class FilterWeatherDataTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsThisDataWithinHoursOfInterest_DataWellWithInInterest_ReturnsTrue() {

		String forecastDateAsString = "2017-03-29T12:00:00+1100"; 

		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, true, filter);

	}

	@Test
	public void testIsThisDataWithinHoursOfInterest_DataAtTheRightEdge_ReturnsTrue() {

		String forecastDateAsString = "2017-03-29T16:00:00+1100";
		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, true, filter);

	}

	@Test
	public void testIsThisDataWithinHoursOfInterest_DataAtTheLeftEdge() {

		String forecastDateAsString = "2017-03-29T08:00:00+1100";
		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, true, filter);

	}

	@Test
	public void testIsThisDataWithinHoursOfInterest_DataWellOutsideTheWindow() {

		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();
		String forecastDateAsString = "2017-03-29T07:00:00+1100";
		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, false, filter);

	}

	@Test
	public void testIsThisDataWithinHoursOfInterest_DataJustOutsideTheWindow1() {

		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();
		String forecastDateAsString = "2017-03-29T07:59:59+1100";
		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, false, filter);

	}

	@Test
	//This should return true. Anything that has the same hour as the  schoolTimings is good (ignoring the minutes and seconds values)
	public void testIsThisDataWithinHoursOfInterest_DataJustOutsideTheWindow2() {

		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		String forecastDateAsString = "2017-03-29T16:00:01+1100";
		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, true, filter);

	}
	
	@Test
	//This should return true. Anything that has the same hour as the  schoolTimings is good (ignoring the minutes and seconds values)
	public void testIsThisDataWithinHoursOfInterest_DataWellOutsideTheWindow_isStillConsideredGood() {

		FilterWeatherData filter = new FilterWeatherData();
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		String forecastDateAsString = "2017-03-29T16:59:59+1100";
		commonCodeToTestDataWithinHoursOfInterest(forecastDateAsString, schoolTimings, true, filter);

	}

	/**
	 * Using some common code instead of repeating them in each Test method..
	 * 
	 * @param forecastDateAsString
	 * @param schoolTimings
	 * @param expectedValue
	 * @param filter
	 */
	private void commonCodeToTestDataWithinHoursOfInterest(String forecastDateAsString, SchoolTimings schoolTimings,
			boolean expectedValue, FilterWeatherData filter) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
			Date aDate = sdf.parse(forecastDateAsString);

			assertEquals(expectedValue, filter.isThisDataWithinHoursOfInterest(aDate, schoolTimings));

		} catch (ParseException e) {
			fail("Parse exception" + e.getMessage(), e);

		}

	}
}
