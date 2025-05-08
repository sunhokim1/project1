package com.pro.vo;

import java.time.LocalDate;



public class BookingDate implements Comparable<BookingDate> {
/**
 * <pre>
 * {@code
 * BookingDate 클래스는 예약날짜의 정보를 가지고 있는 클래스이다.  
 * }
 * </pre>
 * @author LEE TAE RAN
 * @version project version 1.0.2
 * @since JDK17
 * 
 */
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
	
	public int compareTo(BookingDate b) {
		return this.date.compareTo(b.getDate());
	}
	
	

}
