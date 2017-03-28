package com.forecastessentials.school.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forecastessentials.school.domain.Coordinates;
import com.forecastessentials.school.services.ProcessWeatherInformation;

@RestController
public class SchoolUniformForecastController {

	@Autowired
	ProcessWeatherInformation weatherInfoServiceImpl;

	@GetMapping("/simple")
	public String getDressForecastInformation() {

		// Simplest Implementation - Invoke the services Layer and let's see
		// what happens...

		double latitude = -37.8828820d;
		double longitude = 145.1776060d;

		Coordinates theSchoolCoordiantes = new Coordinates(latitude, longitude);

		weatherInfoServiceImpl.getSimplifiedForecast(7, theSchoolCoordiantes);

		return "hello- I am still working things out";
	}

}
