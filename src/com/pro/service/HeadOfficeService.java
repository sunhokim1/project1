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

}
