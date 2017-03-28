package com.forecastessentials.school.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forecastessentials.school.domain.Coordinates;
import com.forecastessentials.school.services.proxy.WeatherCompanyAPIProxy;

import feign.FeignException;

@Component
public class ProcessWeatherInformation {

	private static final Logger logger = LoggerFactory.getLogger(ProcessWeatherInformation.class);

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

			//response = weatherServiceProxy.getWeatherForecastHourlyInfo(String.valueOf(schoolCoordiates.getLatitude()),String.valueOf(schoolCoordiates.getLongitude()));
			
			//response = weatherServiceProxy.getWeatherForecastHourlyInfoWithHardCodedInformation();
			
			//System.out.println(weatherServiceProxy.getCountries());
			
			//This one works..
			//System.out.println(weatherServiceProxy.getCountryInfoHardcodedInFeignClientAnnotation());

			//System.out.println(weatherServiceProxy.getCountryInfo("India"));
			
			System.out.println("Calling the hardcoded info in 'method' annotation.....");
			
			System.out.println(weatherServiceProxy.getCountryInfoHardcodedInMethodAnnotation());
			
			System.out.println("Calling the method which uses 'PATHVARIALE' Annotation");
			
			System.out.println(weatherServiceProxy.getCountryInfoUsingPathVariable("Australia"));
			
			logger.info("Response from weather API Call is:" + response);

		} catch (FeignException e) {

			e.printStackTrace();
		}

		return null;
	}
}
