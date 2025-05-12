package com.pro.service.impl.test;

import com.pro.service.impl.HeadOfficeServiceImpl;
import com.pro.vo.*;
import com.pro.vo.child.Guest;

import java.time.LocalDate;
import java.util.HashMap;

public class HeadOfficeServiceImplTest {
    public static void main(String[] args) {
        HeadOfficeServiceImpl service = HeadOfficeServiceImpl.getInstance();

        // 사용자 및 게스트하우스 초기 등록
        Guest guest1 = new Guest("guest001", "홍길동", "010-1111-2222", "한국");
        Guest guest2 = new Guest("guest002", "김영희", "010-2222-3333", "한국");
        GuestHouse house = new GuestHouse(
                "서울하우스", "서울 강남구",
                createRoomMap("101", 2, "102", 1),
                "한강공원", 100000);

        try {
            service.addUser(guest1);
            service.addUser(guest2);
            service.addGuestHouse(house);
        } catch (Exception e) {
            System.out.println("초기 등록 중 예외 발생: " + e.getMessage());
        }

        // 예약 1: guest1 예약 (101)
        Booking booking1 = new Booking(guest1, house, "101",
                new BookingDate(LocalDate.of(2025, 5, 10)),
                new BookingDate(LocalDate.of(2025, 5, 12)));
        service.addBook(booking1);

        // 예약 2: guest2 예약 (101) - 정원 2명이므로 가능
        Booking booking2 = new Booking(guest2, house, "101",
                new BookingDate(LocalDate.of(2025, 5, 10)),
                new BookingDate(LocalDate.of(2025, 5, 12)));
        service.addBook(booking2);

        // 예약 3: guest1이 다시 101 시도 - 초과되므로 실패해야 함
        Booking booking3 = new Booking(guest1, house, "101",
                new BookingDate(LocalDate.of(2025, 5, 10)),
                new BookingDate(LocalDate.of(2025, 5, 12)));
        service.addBook(booking3);

        // 예약 4: guest1이 102 시도 (1명 정원)
        Booking booking4 = new Booking(guest1, house, "102",
                new BookingDate(LocalDate.of(2025, 5, 13)),
                new BookingDate(LocalDate.of(2025, 5, 14)));
        service.addBook(booking4);

        // 예약 5: guest2가 102 시도 (이미 찼으므로 실패)
        Booking booking5 = new Booking(guest2, house, "102",
                new BookingDate(LocalDate.of(2025, 5, 13)),
                new BookingDate(LocalDate.of(2025, 5, 14)));
        service.addBook(booking5);

        // 예약 현황 출력
        printBookings("[예약 현황 확인]", service);

        // 특정 날짜 만실 여부 확인
        LocalDate date1 = LocalDate.of(2025, 5, 10);
        LocalDate date2 = LocalDate.of(2025, 5, 13);
        System.out.println("[만실 확인] " + date1 + ": " + service.isroomFull(date1));
        System.out.println("[만실 확인] " + date2 + ": " + service.isroomFull(date2));
    }

    private static HashMap<String, Integer> createRoomMap(String r1, int p1, String r2, int p2) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(r1, p1);
        map.put(r2, p2);
        return map;
    }

    private static void printBookings(String title, HeadOfficeServiceImpl service) {
        System.out.println("\n" + title);
        for (Booking b : service.getBooks()) {
            System.out.println(b);
        }
    }
}