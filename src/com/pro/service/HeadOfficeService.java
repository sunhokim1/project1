package com.pro.service;

import java.util.List;
import com.pro.vo.User;
import com.pro.exception.DuplicateException;
import com.pro.exception.RecordNotFoundException;
import com.pro.vo.Booking;
import com.pro.vo.GuestHouse;
/**
 * HeadOfficeService 인터페이스는 BookingService와 RevenueService를 상속받아,
 * 본사 차원에서 게스트하우스 및 사용자 관리를 위한 기능을 제공한다.
 *
 * @author LEE TAE RAN
 * @version 1.0.0
 * @since JDK17
 */
public interface  HeadOfficeService extends BookingService,RevenueService{
	/**
     * 시스템에 새로운 게스트하우스를 추가한다.
     *
     * @param guestHouse 추가할 GuestHouse Entity
     */
	void addGuestHouse(GuestHouse guestHouse)throws DuplicateException;
	/**
     * 시스템에 새로운 사용자를 추가한다.
     *
     * @param user 추가할 User Entity
     */
	void addUser(User user)throws DuplicateException;
	/**
     * 지정된 이름의 게스트하우스를 시스템에서 삭제한다.
     *
     * @param name 삭제할 게스트하우스의 고유 이름
     */
	void deleteGuestHouse(String name)throws RecordNotFoundException;
	 /**
     * 지정된 ID를 가진 사용자를 시스템에서 삭제한다.
     *
     * @param id 삭제할 사용자의 고유 id
     */
	void deleteUser(String id)throws RecordNotFoundException;
	/**
     * 지정된 ID를 가진 사용자를 조회한다.
     *
     * @param id 조회할 사용자의 id
     * @return 조회된 User 객체, 없으면 null 반환
     */
	User searchUser(String id);
	 /**
     * 시스템에 등록된 모든 사용자를 조회한다.
     *
     * @return 등록된 모든 User Entity 목록
     */
	List<User> searchAllUsers();

    /**
     * 지정된 이름의 게스트하우스를 조회한다.
     *
     * @param name 조회할 게스트하우스의 고유 이름
     * @return 조회된 GuestHouse 객체, 없으면 null 반환
     */
	GuestHouse searchGuestHouse(String name);
	/**
     * 시스템에 등록된 모든 게스트하우스를 조회한다.
     *
     * @return 등록된 모든 GuestHouse Entity 목록
     */
	List<GuestHouse> searchAllGuestHouse();
	/**
     * 지정된 User 엔티티 정보를 기반으로 사용자를 업데이트한다.
     *
     * @param user 업데이트할 User Entity
     */
	void updateUser(User user) throws RecordNotFoundException;
	/**
     * 지정된 GuestHouse 엔티티 정보를 기반으로 게스트하우스를 업데이트한다.
     *
     * @param guesthouse 업데이트할 GuestHouse Entity
     */
	void updateGuestHouse(GuestHouse guesthouse)throws RecordNotFoundException;

}
