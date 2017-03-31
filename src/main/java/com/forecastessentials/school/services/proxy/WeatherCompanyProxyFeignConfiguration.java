package com.forecastessentials.school.services.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;

@Configuration
public class WeatherCompanyProxyFeignConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(WeatherCompanyProxyFeignConfiguration.class);

	@Autowired
	private Environment env;

	@Bean
	public feign.Logger feignLogger() {
		return new Slf4jLogger(WeatherCompanyProxyFeignConfiguration.class);
	}

	@Bean(name = "weather-company-request-interceptor")
	public RequestInterceptor basicAuthRequestInterceptor() {

		String username = null;
		String password = null;

		logger.info("Info on username for weather-company calls:" + env.getProperty("weathercompany.username"));
		logger.debug("Info on password for weather-company calls:" + env.getProperty("weathercompany.password"));

		username = env.getProperty("weathercompany.username"); // "78d36bc4-71bc-43df-8932-515714081afc"
		password = env.getProperty("weathercompany.password"); // "8Ruc5k9T4e"

		// username = "78d36bc4-71bc-43df-8932-515714081afc";
		// password = "8Ruc5k9T4e";

		return new BasicAuthRequestInterceptor(username, password);
	}
}