package com.pro.vo;

import java.time.LocalDate;



public class BookingDate implements Comparable<BookingDate> {
	private LocalDate date;

	public BookingDate(LocalDate date) {
		super();
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BookingDate [date=" + date + "]";
	}
	
	public int compareTo(BookingDate b) {
		return this.date.compareTo(b.getDate());
	}
	
	

}
