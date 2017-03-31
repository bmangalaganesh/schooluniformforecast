/**
 * 
 */
package com.forecastessentials.school.services;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.forecastessentials.school.domain.SchoolTimings;

/**
 * This Filters the forecast data based on the various Filter specified
 * 
 * @author Manglu
 *
 */
@Component
public class FilterWeatherData {

	/**
	 * Look at the time component and check if it is within the hours of
	 * interest (provided by the schoolTimings)
	 * 
	 * The interest is only the hour. The minutes and seconds field are ignored
	 * as the forecast hours are only provided on a hourly basis.
	 * 
	 * @param theForecastTime
	 * @return TODO Change method visibility to private later...
	 */
	@Deprecated
	public boolean isThisDataWithinHoursOfInterest(Date theForecastTime, SchoolTimings schoolTimings) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theForecastTime);

		int thehour = calendar.get(Calendar.HOUR_OF_DAY);

		if (thehour >= schoolTimings.getStartTime() && thehour <= schoolTimings.getEndTime()) {
			return true;
		} else
			return false;
	}

	public boolean isThisDataWithinHoursOfInterest(OffsetDateTime theForecastTime, SchoolTimings schoolTimings) {

		int thehour = theForecastTime.getHour();

		if (thehour >= schoolTimings.getStartTime() && thehour <= schoolTimings.getEndTime()) {
			return true;
		} else
			return false;
	}

}
