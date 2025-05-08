package com.pro.service;

import java.util.List;

import com.pro.vo.Booking;

public interface BookingService {
	void addBook(Booking booking);
	Booking getBook(String guestName,String phone);
	void updateBook(Booking booking);
	void cancelBook(String guestName,String phone);
	List<Booking> getBooks();
	List<Booking> getBooks(String guestId);
	Boolean isroomFull();

}
