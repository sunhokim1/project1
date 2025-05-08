package com.pro.service;

import java.util.List;
import com.pro.vo.User;
import com.pro.vo.Booking;
import com.pro.vo.GuestHouse;


public interface  HeadOfficeService extends BookingService,RevenueService{
	void addGuestHouse(GuestHouse guestHouse);
	void addUser(User user);
	void deleteGuestHouse(String name);
	void deleteUser(String id);
	User searchUser(String id);
	List<User> searchAllUsers();
	GuestHouse searchGuestHouse(String name);
	List<GuestHouse> searchAllGuestHouse();
	void updateUser(User user);
	void updateGuestHouse(GuestHouse guesthouse);
	
	void addBook(Booking booking);
	Booking getBook(String guestName,String phone);
	void updateBook(Booking booking);
	void cancelBook(String guestName,String phone);
	List<Booking> getBooks();
	List<Booking> getBooks(String guestId);
	Boolean isroomFull();
	
	double getSalesForMonth(int year, int month);
	double getSalesForQuarter(int year, int quarter);
	double getSalesForYear(int year);
	double getSalesForMonth(int year, int month, String guestHouseName);
	double getSalesForQuarter(int year, int quarter, String guestHouseName);
	double getSalesForYear(int year, String guestHouseName);
	int getPeakSeason(int year);

}
