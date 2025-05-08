package com.pro.service.impl.test;

import java.util.HashMap;
import java.util.Map;

import com.pro.service.impl.HeadOfficeServiceImpl;
import com.pro.vo.GuestHouse;
import com.pro.vo.User;
import com.pro.vo.child.Employee;
import com.pro.vo.child.Guest;

public class HeadOfficeServiceImplTest {

	public static void main(String[] args) {
		HeadOfficeServiceImpl service;
		service = HeadOfficeServiceImpl.getInstance();
		System.out.println("======================1. AddUserTest======================");
		service.addUser(new Guest("g_101", "김철수", "010-1234-5678", "한국"));
		service.addUser(new Guest("g_202", "안나", "010-3334-5655", "외국"));
		service.addUser(new Guest("g_303", "화성인", "050-0054-5351", "우주"));
		service.addUser(new Employee("e_101", "박관리", "070-1123-3345", "하우스매니저", 2000.0, 250.0));
		System.out.println();
		System.out.println("======================2. SearchAllUsersTest===============");
		for (User u : service.searchAllUsers()) {
			System.out.println(u);
		}
		System.out.println();
		System.out.println("======================3. DeleteUserTest======================");
		service.deleteUser("g_101");
		for (User u : service.searchAllUsers()) {
			System.out.println(u);
		}
		System.out.println();
		System.out.println("======================4. UpdateUserTest======================");
		service.updateUser(new Employee("e_101", "박관리", "010-3143-9950", "홍보부", 1000.0, 0.0));
		System.out.println();
		System.out.println("======================5. searchUserTest======================");
		System.out.println(service.searchUser("e_101"));
		System.out.println();
		System.out.println("======================6. AddGuestHouseTest===================");
		service.addGuestHouse(new GuestHouse("게하A", "게하A주소", new HashMap<>(Map.of("a1", 4, "a2", 4, "a3", 4, "a4", 4)), "드라마촬영지", 50));
		service.addGuestHouse(new GuestHouse("게하B", "게하B주소", new HashMap<>(Map.of("a1", 4, "a2", 4, "a3", 4, "a4", 4)), "경복궁", 65));
		service.addGuestHouse(new GuestHouse("게하C", "게하C주소", new HashMap<>(Map.of("b1", 6, "b2", 6, "b3", 6, "b4", 6)), "에펠탑", 80));
		System.out.println();
		System.out.println("======================7. SearchAllGuestHouseTest=============");
		for (GuestHouse g : service.searchAllGuestHouse()) {
			System.out.println(g);
		}
		System.out.println();
		System.out.println("======================8. DeleteGuestHouseTest================");
		service.deleteGuestHouse("게하B");
		for (GuestHouse g : service.searchAllGuestHouse()) {
			System.out.println(g);
		}
		System.out.println();
		System.out.println("======================9. UpdateGuestHouseTest================");
		service.updateGuestHouse(new GuestHouse("게하A", "게하A주소", new HashMap<>(Map.of("a1", 4, "a2", 4, "a3", 4, "a4", 4)), "UFO출현지역", 100));
		System.out.println();
		System.out.println("=====================10. SearchGuestHouseTest================");
		System.out.println(service.searchGuestHouse("게하A"));
	}

}
