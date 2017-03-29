package com.forecastessentials.school.domain;

public class SimplifiedWeatherData {

	public enum temperature {
		FREEZING_COLD, SLIGHTLY_COLD, COLD, PLEASANT, HOT, EXTREMRELY_HOT
	}

	public enum rain {
		LIGHT_SHOWERS, SHOWERS, DRY, OCCASIONAL_SHOWERS
	}

	// TODO uv is at the moment is copy-paste data which needs to be fixed
	public enum uv {
		LIGHT_SHOWERS, SHOWERS, DRY, OCCASIONAL_SHOWERS
	}

	// Given a WeatherDataResponse, the constructor creates the
	// SimplifiedWeatherData based on simple rules.
	/*
	 * I will have to explore the usage of rules here (and this should be based
	 * on user's preferences)
	 * 
	 */
	public SimplifiedWeatherData(WeatherDataResponse theWeatherData) {
		
	}

	
	
	private String dressProtection;

	private String rainProtection;

	private String sunProtection;

	public String getDressProtection() {
		return dressProtection;
	}

	public void setDressProtection(String dressProtection) {
		this.dressProtection = dressProtection;
	}

	public String getRainProtection() {
		return rainProtection;
	}

	public void setRainProtection(String rainProtection) {
		this.rainProtection = rainProtection;
	}

	public String getSunProtection() {
		return sunProtection;
	}

	public void setSunProtection(String sunProtection) {
		this.sunProtection = sunProtection;
	}

}
