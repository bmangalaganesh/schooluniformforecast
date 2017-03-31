package com.forecastessentials.school.domain;

/**
 * Represents the School timings in 24 hours format (in the local time zone)
 * 
 * Use the integer format for the start and end timings and include the travel
 * time as well.
 * 
 * @author Manglu
 *
 */
public class DefaultSchoolTimings implements SchoolTimings {

	private int startTime = 8;
	private int endTime = 16;

	/* (non-Javadoc)
	 * @see com.forecastessentials.school.domain.SchoolTimings#getStartTime()
	 */
	@Override
	public int getStartTime() {
		return startTime;
	}

	/* (non-Javadoc)
	 * @see com.forecastessentials.school.domain.SchoolTimings#setStartTime(int)
	 */
	@Override
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/* (non-Javadoc)
	 * @see com.forecastessentials.school.domain.SchoolTimings#getEndTime()
	 */
	@Override
	public int getEndTime() {
		return endTime;
	}

	/* (non-Javadoc)
	 * @see com.forecastessentials.school.domain.SchoolTimings#setEndTime(int)
	 */
	@Override
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
