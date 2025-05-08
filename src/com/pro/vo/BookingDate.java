package com.pro.vo;

import java.time.LocalDate;
/**
 * VO class representing a booking date for availability checks.
 */
public class BookingDate {
	/**
     * 조회 대상 날짜 (체크인 또는 체크아웃과 무관한 하루 단위)
     */
	private LocalDate date;

	/**
     * Constructs a new BookingDate VO.
     *
     * @param date 조회할 대상 날짜
     */
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

	/**
     * @return string representation
     */
	@Override
	public String toString() {
		return "BookingDate [date=" + date + "]";
	}
	
	

}
