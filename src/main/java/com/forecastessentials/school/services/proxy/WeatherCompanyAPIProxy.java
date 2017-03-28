package com.forecastessentials.school.services.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "weather-company-service" , url = "http://3981aee7-448c-4ea3-a254-bac1782cda37:2mSEubBc3i@twcservice.mybluemix.net/api/weather/v1/geocode" )
//@FeignClient(name = "country-service-client", url = "https://country.io")
//@FeignClient(name = "weather-company-service", url = "http://restcountries.eu/rest/v2/name/Australia")
@FeignClient(name = "another-country-service", url = "http://restcountries.eu/rest/v2")


public interface WeatherCompanyAPIProxy {

	// Request URL - GET
	// /v1/geocode/{latitude}/{longitude}/forecast/hourly/48hour.json
	// Example curl -X GET --header 'Accept: application/json'
	// 'https://twcservice.mybluemix.net/api/weather/v1/geocode/33.40/-83.42/forecast/hourly/48hour.json'

	@RequestMapping(method = RequestMethod.GET, value = "/{latitude}/{longitude}/forecast/hourly/48hour.json", produces = "application/json")
	public String getWeatherForecastHourlyInfo(@PathVariable("latitude") String latitude,
			@PathVariable("longitude") String longitude);

	// This one seems to work, while the one above does not...
	@RequestMapping(method = RequestMethod.GET, value = "/names.json", produces = "application/json")
	String getCountries();

	@RequestMapping(method = RequestMethod.GET, value = "/name/{country}", produces = "application/json")
	public String getCountryInfo(@PathVariable("country") String country);

	@RequestMapping(method = RequestMethod.GET, value = "/-37.8828820/145.1776060/forecast/hourly/48hour.json", produces = "application/json")
	public String getWeatherForecastHourlyInfoWithHardCodedInformation();

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String getCountryInfoHardcodedInFeignClientAnnotation();

	@RequestMapping(method = RequestMethod.GET, value = "/name/Australia", produces = "application/json")
	public String getCountryInfoHardcodedInMethodAnnotation();
	
	@RequestMapping(method = RequestMethod.GET, value = "/name/{country}", produces = "application/json")
	public String getCountryInfoUsingPathVariable(@PathVariable("country") String country);
}
