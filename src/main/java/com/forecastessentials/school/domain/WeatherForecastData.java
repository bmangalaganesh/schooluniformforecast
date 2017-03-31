package com.forecastessentials.school.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * { "class": "fod_short_range_hourly", "expire_time_gmt": 1490760120,
 * "fcst_valid": 1490763600, "fcst_valid_local": "2017-03-29T16:00:00+1100",
 * "num": 2, "day_ind": "D", "temp": 79, "dewpt": 48, "hi": 79, "wc": 79,
 * "feels_like": 79, "icon_extd": 3000, "wxman": "wx1100", "icon_code": 30,
 * "dow": "Wednesday", "phrase_12char": "P Cloudy", "phrase_22char": "Partly
 * Cloudy", "phrase_32char": "Partly Cloudy", "subphrase_pt1": "Partly",
 * "subphrase_pt2": "Cloudy", "subphrase_pt3": "", "pop": 0, "precip_type":
 * "rain", "qpf": 0, "snow_qpf": 0, "rh": 34, "wspd": 19, "wdir": 310,
 * "wdir_cardinal": "NW", "gust": 28, "clds": 31, "vis": 10, "mslp": 29.65,
 * "uv_index_raw": 3.3, "uv_index": 3, "uv_warning": 0, "uv_desc": "Moderate",
 * "golf_index": 9, "golf_category": "Very Good", "severity": 1 },
 * 
 * @author Manglu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastData {

	private int feelsLike;
	private String temp;
	private String hourOfForecast;

	// This containst the Date representation of the time specified in
	// hourOfForecast
	private Date forecastTime;
	private String weatherPhrase;
	private String uvIndex;
	private String dayOfWeek;
	private String uvWarning;

	private String minimumTemperature;
	private String maximumTemperature;

	public int getFeels_like() {
		return feelsLike;
	}

	@JsonProperty("feels_like")
	public void setFeels_like(int feels_like) {
		this.feelsLike = feels_like;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHourOfForecastl() {
		return hourOfForecast;
	}

	@JsonProperty("fcst_valid_local")
	public void setHourOfForecast(String fcst_valid_local) {
		this.hourOfForecast = fcst_valid_local;
		
		//Set the forecast Time as well here..
		// "fcst_valid_local": "2017-03-29T16:00:00+1100"
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
			this.setForecastTime(sdf.parse(fcst_valid_local));
		} catch (ParseException e) {
			// The data should be good. Will have to think about how to handle this error..
			//For the moment - swallow the error and move on...
			e.printStackTrace();
		}
		
	}

	public String getWeatherPhrase() {
		return weatherPhrase;
	}

	@JsonProperty("phrase_32char")
	public void setWeatherPhrase(String weatherPhrase) {
		this.weatherPhrase = weatherPhrase;
	}

	public String getUvIndex() {
		return uvIndex;
	}

	

	@JsonProperty("uv_index_raw")
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	@JsonProperty("dow")
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getUvWarning() {
		return uvWarning;
	}

	@JsonProperty("uv_warning")
	public void setUvWarning(String uvWarning) {
		this.uvWarning = uvWarning;
	}

	public String getMinimumTemperature() {
		return minimumTemperature;
	}

	@JsonProperty("min_temp")
	public void setMinimumTemperature(String minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}

	public String getMaximumTemperature() {
		return maximumTemperature;
	}

	@JsonProperty("max_temp")
	public void setMaximumTemperature(String maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}

	public Date getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(Date forecastTime) {
		this.forecastTime = forecastTime;
	}

}
