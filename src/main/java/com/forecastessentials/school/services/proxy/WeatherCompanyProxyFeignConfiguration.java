package com.forecastessentials.school.services.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.slf4j.Slf4jLogger;

@Configuration
public class WeatherCompanyProxyFeignConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public feign.Logger feignLogger() {
		return new Slf4jLogger(WeatherCompanyProxyFeignConfiguration.class);
	}

	@Bean(name = "weather-company-request-interceptor")
	public RequestInterceptor basicAuthRequestInterceptor() {
		
		String username = env.getProperty("weathercompany.username"); //"78d36bc4-71bc-43df-8932-515714081afc"
		String password = env.getProperty("weathercompany.password"); //"8Ruc5k9T4e"
		
		
		
		
		
		return new BasicAuthRequestInterceptor(username,password );
	}
}