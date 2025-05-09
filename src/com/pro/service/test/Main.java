package com.pro.service.test;

import java.util.Scanner;

import com.pro.service.impl.HeadOfficeServiceImpl;
import com.pro.vo.child.Guest;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HeadOfficeServiceImpl service;
		service = HeadOfficeServiceImpl.getInstance();
		int num = sc.nextInt();
		System.out.println("=============Head Office Service==============");
		System.out.println("용도를 선택해 주세요.");
		System.out.println("1. 게스트용 | 2. 직원용 | 3. 종료");
		switch(num) {
			case 1 :
				guestSystem();
				break;
			case 2 :
				employeeService();
				break;
			default : 
				System.out.println("올바르지 않은 입력입니다.");
		}
		System.out.println("프로그램을 종료합니다.");
		sc.close();
	}

	public static void guestSystem() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		HeadOfficeServiceImpl service;
		service = HeadOfficeServiceImpl.getInstance();
		while (run) {
			int num = sc.nextInt();
			System.out.println("===================================================");
			System.out.println("=================Guesthouse Service================");
			System.out.println();
			System.out.println("========================Menu=======================");
			System.out.println("1. 게스트 등록 | 2. 게스트하우스 예약 | 3. 예약 확인 | 4. 예약 변경 | 5. 예약 취소 | 6. 종료");
		
			switch (num) {
				case 1 :
					
					service.addUser(new Guest(null, null, null, null));
					break;
					
			}
		}
	}
	public static void employeeService() {
		
	}
}
