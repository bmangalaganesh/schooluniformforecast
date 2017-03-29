package com.forecastessentials.school.services;

import java.io.IOException;
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

			//The default units are in Faregnheit and not celsius. Forcing it to use that attribute results in a CSRF (Cross-Site Request Forgery)
			//response = weatherServiceProxy.getWeatherForecastHourlyInfo(String.valueOf(schoolCoordiates.getLatitude()),String.valueOf(schoolCoordiates.getLongitude()),METRICS_SYSTEM);
			
			response = weatherServiceProxy.getWeatherForecastHourlyInfo(String.valueOf(schoolCoordiates.getLatitude()),String.valueOf(schoolCoordiates.getLongitude()));
			
			
			//Response is a String we need to convert that into a JSON object of interest.
			ObjectMapper mapper = new ObjectMapper();
			
			//Convert JSON from String to Object
			WeatherDataResponse weatherInfo = mapper.readValue(response, WeatherDataResponse.class);
		
			System.out.println("JSON Representation of response is:"+ weatherInfo);

			//JSON from file to Object
			//Staff obj = mapper.readValue(new File("c:\\file.json"), Staff.class);

			//JSON from URL to Object
			//Staff obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);

			
			
			
			//response = weatherServiceProxy.getWeatherForecastHourlyInfoWithHardCodedInformation();
			
			//System.out.println(weatherServiceProxy.getCountries());
			
			//This one works..
			//System.out.println(weatherServiceProxy.getCountryInfoHardcodedInFeignClientAnnotation());

			//System.out.println(weatherServiceProxy.getCountryInfo("India"));
			/*
			System.out.println("Calling the hardcoded info in 'method' annotation.....");
			
			System.out.println(weatherServiceProxy.getCountryInfoHardcodedInMethodAnnotation());
			
			System.out.println("Calling the method which uses 'PATHVARIALE' Annotation");
			
			System.out.println(weatherServiceProxy.getCountryInfoUsingPathVariable("Australia"));
			
			*/
			
			
			HashMap<Date,SimplifiedWeatherData> theWeatherInfo = new HashMap();
			
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
	 * Given the HourlyForecastData for a day 
	 * @param weatherDataForADay
	 */
	private void computeSimplifiedWeatherDataGivenHourlyInformation(List<WeatherForecastData> weatherDataForADay){
		
		
	}
}
