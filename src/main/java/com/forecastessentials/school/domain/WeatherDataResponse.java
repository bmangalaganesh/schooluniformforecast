package com.forecastessentials.school.domain;

import java.util.List;

/**
 * This is the object representation of the result coming from weather.com
 * 
 * The JSON representations looks as shown below
 * 
 * {
  "metadata": {
    "language": "en-US",
    "transaction_id": "1490759989228:-1623709273",
    "version": "1",
    "latitude": -37.88,
    "longitude": 145.17,
    "units": "e",
    "expire_time_gmt": 1490760120,
    "status_code": 200
  },
  "forecasts": [
    {
      "class": "fod_short_range_hourly",
      "expire_time_gmt": 1490760120,
      "fcst_valid": 1490760000,
      "fcst_valid_local": "2017-03-29T15:00:00+1100",
      "num": 1,
      "day_ind": "D",
      "temp": 78,
      "dewpt": 49,
      "hi": 78,
      "wc": 78,
      "feels_like": 78,
      "icon_extd": 3000,
      "wxman": "wx1100",
      "icon_code": 30,
      "dow": "Wednesday",
      "phrase_12char": "P Cloudy",
      "phrase_22char": "Partly Cloudy",
      "phrase_32char": "Partly Cloudy",
      "subphrase_pt1": "Partly",
      "subphrase_pt2": "Cloudy",
      "subphrase_pt3": "",
      "pop": 0,
      "precip_type": "rain",
      "qpf": 0,
      "snow_qpf": 0,
      "rh": 36,
      "wspd": 19,
      "wdir": 318,
      "wdir_cardinal": "NW",
      "gust": 29,
      "clds": 35,
      "vis": 10,
      "mslp": 29.68,
      "uv_index_raw": 5.01,
      "uv_index": 5,
      "uv_warning": 0,
      "uv_desc": "Moderate",
      "golf_index": 9,
      "golf_category": "Very Good",
      "severity": 1
    },
    {
      "class": "fod_short_range_hourly",
      "expire_time_gmt": 1490760120,
      "fcst_valid": 1490763600,
      "fcst_valid_local": "2017-03-29T16:00:00+1100",
      "num": 2,
      "day_ind": "D",
      "temp": 79,
      "dewpt": 48,
      "hi": 79,
      "wc": 79,
      "feels_like": 79,
      "icon_extd": 3000,
      "wxman": "wx1100",
      "icon_code": 30,
      "dow": "Wednesday",
      "phrase_12char": "P Cloudy",
      "phrase_22char": "Partly Cloudy",
      "phrase_32char": "Partly Cloudy",
      "subphrase_pt1": "Partly",
      "subphrase_pt2": "Cloudy",
      "subphrase_pt3": "",
      "pop": 0,
      "precip_type": "rain",
      "qpf": 0,
      "snow_qpf": 0,
      "rh": 34,
      "wspd": 19,
      "wdir": 310,
      "wdir_cardinal": "NW",
      "gust": 28,
      "clds": 31,
      "vis": 10,
      "mslp": 29.65,
      "uv_index_raw": 3.3,
      "uv_index": 3,
      "uv_warning": 0,
      "uv_desc": "Moderate",
      "golf_index": 9,
      "golf_category": "Very Good",
      "severity": 1
    },
   ]
}
 * @author Manglu
 *
 */
public class WeatherDataResponse {
	
	public WeatherDataResponseMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(WeatherDataResponseMetadata metadata) {
		this.metadata = metadata;
	}

	public List<WeatherForecastData> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<WeatherForecastData> forecasts) {
		this.forecasts = forecasts;
	}

	private  WeatherDataResponseMetadata metadata;
	
	private List<WeatherForecastData> forecasts ; 
}
