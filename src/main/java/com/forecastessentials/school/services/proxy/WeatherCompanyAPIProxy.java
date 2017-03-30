package com.forecastessentials.school.services.proxy;

import javax.inject.Named;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "weather-company-service" , url = "http://3981aee7-448c-4ea3-a254-bac1782cda37:2mSEubBc3i@twcservice.mybluemix.net/api/weather/v1/geocode" ,configuration = WeatherCompanyProxyFeignConfiguration.class)
//@FeignClient(name = "country-service-client", url = "https://country.io")
//@FeignClient(name = "weather-company-service", url = "http://restcountries.eu/rest/v2/name/Australia")
//@FeignClient(name = "another-country-service", url = "https://restcountries.eu/rest/v2")
/**
 * Invoking an API call for the first time always results in a Hystrix time-out exception.
 * 
 * java.util.concurrent.TimeoutException: null
	at com.netflix.hystrix.AbstractCommand.handleTimeoutViaFallback(AbstractCommand.java:958) ~[hystrix-core-1.5.3.jar:1.5.3]
	at com.netflix.hystrix.AbstractCommand.access$400(AbstractCommand.java:59) ~[hystrix-core-1.5.3.jar:1.5.3]
	at com.netflix.hystrix.AbstractCommand$11.call(AbstractCommand.java:573) ~[hystrix-core-1.5.3.jar:1.5.3]
	at com.netflix.hystrix.AbstractCommand$11.call(AbstractCommand.java:565) ~[hystrix-core-1.5.3.jar:1.5.3]
	at rx.internal.operators.OperatorOnErrorResumeNextViaFunction$4.onError(OperatorOnErrorResumeNextViaFunction.java:140) ~[rxjava-1.1.10.jar:1.1.10]


 * Calling it a second time appears to work well (both http and https)
 * Others have experienced a similar issues - https://github.com/spring-cloud/spring-cloud-netflix/issues/768
 * 
 * @author Manglu
 *
 */

//@Headers("Accept: application/json")
public interface WeatherCompanyAPIProxy {

	// Request URL - GET
	// /v1/geocode/{latitude}/{longitude}/forecast/hourly/48hour.json
	// Example curl -X GET --header 'Accept: application/json'
	// 'https://twcservice.mybluemix.net/api/weather/v1/geocode/33.40/-83.42/forecast/hourly/48hour.json'

	/*
	 * Using this variant results in a CSRF 
	 * 
	 * 2017-03-29 13:57:39.281 DEBUG 52578 --- [nio-8443-exec-6] o.s.c.n.feign.support.SpringEncoder      : Writing [m] using [org.springframework.http.converter.StringHttpMessageConverter@7be7e15]
feign.FeignException: status 403 reading WeatherCompanyAPIProxy#getWeatherForecastHourlyInfo(String,String,String); content:
{"error":{"message":"invalid csrf token","stack":"ForbiddenError: invalid csrf token\n    at Object.csrf [as handle] (/home/vcap/app/node_modules/csurf/index.js:112:19)\n    at next (/home/vcap/app/node_modules/express/node_modules/connect/lib/proto.js:193:15)\n    at Object.csp [as handle] (/home/vcap/app/node_modules/helmet-csp/index.js:62:7)\n    at next (/home/vcap/app/node_modules/express/node_modules/connect/lib/proto.js:193:15)\n    at Object.hsts [as handle] (/home/vcap/app/node_modules/helmet/node_modules/hsts/index.js:56:5)\n    at next (/home/vcap/app/node_modules/express/node_modules/connect/lib/proto.js:193:15)\n    at Object.xXssProtection [as handle] (/home/vcap/app/node_modules/helmet/node_modules/x-xss-protection/index.js:19:7)\n    at next (/home/vcap/app/node_modules/express/node_modules/connect/lib/proto.js:193:15)\n    at Object.nosniff [as handle] (/home/vcap/app/node_modules/helmet/node_modules/dont-sniff-mimetype/index.js:4:5)\n    at Immediate.next (/home/vcap/app/node_modules/express/node_modules/connect/lib/proto.js:193:15)","code":"EBADCSRFTOKEN","status":403,"statusCode":403,"expose":true}}
	at feign.FeignException.errorStatus(FeignException.java:62)
	at feign.codec.ErrorDecoder$Default.decode(ErrorDecoder.java:91)
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:134)
	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:76)
	at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:103)
	at com.sun.proxy.$Proxy87.getWeatherForecastHourlyInfo(Unknown Source)
	at com.forecastessentials.school.services.ProcessWeatherInformation.retrieveForecastFromWeatherProviders(ProcessWeatherInformation.java:50)


	 */
	//@RequestMapping(method = RequestMethod.GET, value = "/{latitude}/{longitude}/forecast/hourly/48hour.json?units={units}", produces = "application/json")
	//public String getWeatherForecastHourlyInfo(@PathVariable("latitude") String latitude,
	//		@PathVariable("longitude") String longitude, @Named("units") String units);

	
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
