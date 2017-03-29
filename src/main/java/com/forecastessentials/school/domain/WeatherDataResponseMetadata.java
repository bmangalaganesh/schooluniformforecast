package com.forecastessentials.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Sample JSON representation of the data "metadata": { "language": "en-US",
 * "transaction_id": "1490759989228:-1623709273", "version": "1", "latitude":
 * -37.88, "longitude": 145.17, "units": "e", "expire_time_gmt": 1490760120,
 * "status_code": 200 }
 * 
 * @author Manglu
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)

public class WeatherDataResponseMetadata {

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getStatus_code() {
		return statusCode;
	}
	
	@JsonProperty("status_code")
	public void setStatus_code(String status_code) {
		this.statusCode = status_code;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	private String language;
	private String statusCode;
	private String units;

}
