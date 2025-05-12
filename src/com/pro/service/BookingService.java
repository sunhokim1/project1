package com.pro.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import com.pro.vo.Booking;
/**
 * BookingService 인터페이스는 예약(Booking) 관리 기능을 제공한다.
 * 예약 생성, 조회, 수정, 취소 및 예약 목록 확인 기능을 정의한다.
 *
 * @author LEE TAE RAN
 * @version 1.0.0
 * @since JDK17
 */
public interface BookingService {
	/**
     * 새로운 예약을 시스템에 추가한다.
     *
     * @param booking 생성할 Booking 엔티티
     */
	void addBook(Booking booking);
	/**
     * 이름과 전화번호를 기반으로 단일 예약 정보를 조회한다.
     *
     * @param guestName 예약자 이름
     * @param phone     예약자 전화번호
     * @return 조회된 Booking 객체, 없으면 null 반환
     */

	void updateBook(int isbn, Booking booking);
	/**
     * 이름과 전화번호를 기반으로 예약을 취소한다.
     *
     * @param guestName 예약자 이름 확인용
     * @param phone     예약자 전화번호 확인용
     */
	void cancelBook(Booking booking);
	/**
     * 시스템에 등록된 모든 예약 목록을 조회한다.
     *
     * @return 모든 Booking 엔티티의 리스트
     */
	List<Booking> getBooks();
	/**
     * 특정 사용자(guestId)의 예약 목록을 조회한다.
     *
     * @param guestId 조회할 사용자 식별자
     * @return 해당 사용자의 Booking 리스트
     */
	Booking getBook(int isbn);
	List<Booking> getBooks(String guestId);
	/**
     * 현재 모든 객실이 예약으로 가득 찼는지 여부를 확인한다.
     *
     * @return 예약 가능 객실이 없으면 true, 그렇지 않으면 false
     */
	Boolean isroomFull(LocalDate current);

}
