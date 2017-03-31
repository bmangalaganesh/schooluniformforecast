package com.forecastessentials.school.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forecastessentials.school.domain.Coordinates;
import com.forecastessentials.school.domain.DefaultSchoolTimings;
import com.forecastessentials.school.domain.SchoolTimings;
import com.forecastessentials.school.domain.SimplifiedWeatherData;
import com.forecastessentials.school.domain.WeatherDataResponse;
import com.forecastessentials.school.domain.WeatherForecastData;
import com.forecastessentials.school.services.proxy.WeatherCompanyAPIProxy;

import feign.FeignException;

@Component
public class ProcessWeatherInformation {

	private static final Logger logger = LoggerFactory.getLogger(ProcessWeatherInformation.class);

	private static final String METRICS_SYSTEM = "m";

	private String weatherServicesURL = "http://3981aee7-448c-4ea3-a254-bac1782cda37:2mSEubBc3i@twcservice.mybluemix.net";

	@Autowired
	WeatherCompanyAPIProxy weatherServiceProxy;
	
	
	@Autowired
	FilterWeatherData dataFilter;

	public List<String> getSimplifiedForecast(int numberOfDays, Coordinates schoolCoordinates) {

		// Invoke the call to the Weather Service Provider...
		retrieveForecastFromWeatherProviders(numberOfDays, schoolCoordinates);

		return null;

	}

	/**
	 * Retrieve the weather forecast information from weather providers. In this
	 * case, I am sticking with the Weather company (an IBM Company).
	 * 
	 * @param numberOfDays
	 * @return
	 */
	private List<String> retrieveForecastFromWeatherProviders(int numberOfDays, Coordinates schoolCoordiates) {

		String response = null;
		try {

			logger.info("About to retrieve forecast information from Weather Provider at URL:" + weatherServicesURL);

			// The default units are in Faregnheit and not celsius. Forcing it
			// to use that attribute results in a CSRF (Cross-Site Request
			// Forgery)
			// response =
			// weatherServiceProxy.getWeatherForecastHourlyInfo(String.valueOf(schoolCoordiates.getLatitude()),String.valueOf(schoolCoordiates.getLongitude()),METRICS_SYSTEM);

			response = weatherServiceProxy.getWeatherForecastHourlyInfo(String.valueOf(schoolCoordiates.getLatitude()),
					String.valueOf(schoolCoordiates.getLongitude()));

			// Response is a String we need to convert that into a JSON object
			// of interest.
			ObjectMapper mapper = new ObjectMapper();

			// Convert JSON from String to Object
			WeatherDataResponse weatherInfo = mapper.readValue(response, WeatherDataResponse.class);

			System.out.println("JSON Representation of response is:" + weatherInfo);

			// Use this response and feed that information to create the
			// SimplifiedWeatherData

			// Parse this data so that a single days' feed is provided to the
			// method

			// Compare the current time with the list and pick up only those
			// that are for the given day.

			HashMap<Date, List<WeatherForecastData>> dayWeatherMap = new HashMap<>();
			createDateWiseWeatherInfo(weatherInfo, new Date(), dayWeatherMap);

			// response =
			// weatherServiceProxy.getWeatherForecastHourlyInfoWithHardCodedInformation();

			// System.out.println(weatherServiceProxy.getCountries());

			// This one works..
			// System.out.println(weatherServiceProxy.getCountryInfoHardcodedInFeignClientAnnotation());

			// System.out.println(weatherServiceProxy.getCountryInfo("India"));
			/*
			 * System.out.
			 * println("Calling the hardcoded info in 'method' annotation....."
			 * );
			 * 
			 * System.out.println(weatherServiceProxy.
			 * getCountryInfoHardcodedInMethodAnnotation());
			 * 
			 * System.out.
			 * println("Calling the method which uses 'PATHVARIALE' Annotation"
			 * );
			 * 
			 * System.out.println(weatherServiceProxy.
			 * getCountryInfoUsingPathVariable("Australia"));
			 * 
			 */

			HashMap<Date, SimplifiedWeatherData> theWeatherInfo = new HashMap();

			logger.info("Response from weather API Call is:" + response);

		} catch (FeignException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Splits the available information into a list of size three. (today,
	 * tomorrow and the DayAfter)
	 * 
	 * We will have to have a filter where the hours of interest can be
	 * specified. For the moment it is going to be 8AM and 4PM
	 * 
	 * @param weatherInfo
	 * @param currentDay
	 * @param dayWeatherMap
	 */
	private void createDateWiseWeatherInfo(WeatherDataResponse weatherInfo, Date currentDay,
			HashMap<Date, List<WeatherForecastData>> dayWeatherMap) {
		
		//Use this as the default for the moment..
		SchoolTimings schoolTimings = new DefaultSchoolTimings();

		// Parse the list so that hourly info is split based on daily basis
		List<WeatherForecastData> forecasts = weatherInfo.getForecasts();

		List<WeatherForecastData> today = new ArrayList<WeatherForecastData>();
		List<WeatherForecastData> tomorrow = new ArrayList<WeatherForecastData>();
		List<WeatherForecastData> theDayAfter = new ArrayList<WeatherForecastData>();

		Date theDateToday = java.sql.Date.valueOf(LocalDate.now());
		Date theDateTomorrow = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
		Date theDateAfterTomorrow = java.sql.Date.valueOf(LocalDate.now().plusDays(2));

		dayWeatherMap.put(theDateToday, today);
		dayWeatherMap.put(theDateTomorrow, tomorrow);
		dayWeatherMap.put(theDateAfterTomorrow, theDayAfter);

		for (WeatherForecastData aForecast : forecasts) {
			// If the day part matches that of currentDay add it to the list

			Date theForecastTime = aForecast.getForecastTime();
			// if aForecast.getFcst_valid_local());

			if (theForecastTime.before(theDateTomorrow)) {
				// This is today...
				// add that to the List of today
				if (dataFilter.isThisDataWithinHoursOfInterest(theForecastTime,schoolTimings))
					today.add(aForecast);
			} else if (theForecastTime.before(theDateAfterTomorrow)) {
				// Filter this to the hours of interest...

				if (dataFilter.isThisDataWithinHoursOfInterest(theForecastTime, schoolTimings)) {
					tomorrow.add(aForecast);
				}
			} else {
				if (dataFilter.isThisDataWithinHoursOfInterest(theForecastTime,schoolTimings))
					theDayAfter.add(aForecast);
			}
		}

	}


	/**
	 * Given the HourlyForecastData for a day
	 * 
	 * @param weatherDataForADay
	 */
	private void computeSimplifiedWeatherDataGivenHourlyInformation(List<WeatherForecastData> weatherDataForADay) {

	}
}
