package com.pro.vo;

import com.pro.vo.child.Guest;



public class Booking implements Comparable<Booking>{
	private int isbn=0;
	private Guest guest;
	private GuestHouse guesthouse;
	private String roomNumber;
	private BookingDate startDate;
	private BookingDate endDate;
	
	public Booking() {}
	public Booking(Guest guest,GuestHouse guesthouse,String roomNumber, BookingDate startDate,BookingDate endDate)
	{
		this.guest = guest;
		this.guesthouse = guesthouse;
		this.roomNumber = roomNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public GuestHouse getGuesthouse() {
		return guesthouse;
	}
	public void setGuesthouse(GuestHouse guesthouse) {
		this.guesthouse = guesthouse;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public BookingDate getStartDate() {
		return startDate;
	}
	public void setStartDate(BookingDate startDate) {
		this.startDate = startDate;
	}
	public BookingDate getEndDate() {
		return endDate;
	}
	public void setEndDate(BookingDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Booking [isbn= "+isbn+", guest=" + guest + ", guesthouse=" + guesthouse + ", roomNumber=" + roomNumber + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	

	/**
	 * compareTo는 게스트하우스를 예약한 시간 중에 시작 시간 기준으로 나열한다.
	 */
	public int compareTo(Booking b) {
		return this.getStartDate().compareTo(b.getStartDate());
	}

	
	
}
