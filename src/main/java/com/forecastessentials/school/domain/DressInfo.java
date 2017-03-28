package com.forecastessentials.school.domain;

/**
 * This is returned as a JSON Object to the caller..
 * 
 * @author Manglu
 *
 */
public class DressInfo {

	private String shirt;
	private String shorts;
	private String umbrella;
	private String hat;

	public boolean isRainy() {
		return isRainy;
	}

	public void setRainy(boolean isRainy) {
		this.isRainy = isRainy;
	}

	public boolean isSunny() {
		return isSunny;
	}

	public void setSunny(boolean isSunny) {
		this.isSunny = isSunny;
	}

	public boolean isCold() {
		return isCold;
	}

	public void setCold(boolean isCold) {
		this.isCold = isCold;
	}

	private boolean isRainy = false;

	private boolean isSunny = false;

	private boolean isCold = false;

}
