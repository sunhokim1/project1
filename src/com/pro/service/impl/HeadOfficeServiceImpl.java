package com.pro.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pro.exception.DuplicateException;
import com.pro.exception.RecordNotFoundException;
import com.pro.service.HeadOfficeService;
import com.pro.vo.Booking;
import com.pro.vo.GuestHouse;
import com.pro.vo.User;
import com.pro.vo.child.Employee;
import com.pro.vo.child.Guest;

/**
 * <pre>
 * {@code
 * HeadOfficeServiceImpl 클래스는 예약시스템과 매출통계 시스템 등을 총괄하는 서비스클래스이다.
 * HeadOfficeService로부터 implemets받아 만들어진다.
 * }
 * </pre>
 * @author Kim SunHo
 * @version project version 0.0.1
 * @since JDK17
 * 
 */
public class HeadOfficeServiceImpl implements HeadOfficeService{
	/**
	 * 싱글턴을 위해 static final로 선언 되었다.
	 */
	private static final HeadOfficeServiceImpl SERVICE = new HeadOfficeServiceImpl();
	/**
	 * Booking 클래스들을 관리하기 위한 List
	 */
	private List<Booking> bookings;
	/**
	 * GuestHouse 클래스들을 관리하기 위한 List
	 */
	private List<GuestHouse> guestHouses;
	/**
	 * User 클래스들을 관리하기 위한 List 
	 * User에서 상속받은 Employee와 Guest를 Polymorpism하게 관리하기 위해 User로 선언
	 */
	private List<User> users;
	/**
	 * 
	 */
	private HashMap<String,Integer> currentRoomStatus;
	
	private int isbn = 0;
	/**
	 * 기본 생성자
	 */
	
	private HeadOfficeServiceImpl(){
		bookings = new ArrayList<Booking>();
		guestHouses = new ArrayList<GuestHouse>();
		users = new ArrayList<User>();
		HashMap<String,Integer> currentRoomStatus = new HashMap<>();
	}
	/**
	 * 싱글턴으로 관리하기 위해 getInstance로만 가져가 쓸수 있도록 하였다.
	 * @return
	 */
	public static HeadOfficeServiceImpl getInstance() {
		return SERVICE;
	}
	
	/**
	 * GuestHouse클래스를 guestHouses ArrayList에 추가한다.
	 */
	@Override
	public void addGuestHouse(GuestHouse guestHouse) throws DuplicateException{//Duplicate
		for (GuestHouse g : guestHouses) {
			if (g.getName().equals(guestHouse.getName())) {
				System.out.println("이미 등록된 게스트하우스입니다.");
				
				return;
			}
		}
		guestHouses.add(guestHouse);
		System.out.println(guestHouse.getName() + "가 목록에 추가되었습니다.");
	}
	/**
	 * User클래스를 users ArrayList에 추가한다.
	 */
	@Override
	public void addUser(User user) throws DuplicateException{//Duplicate
		for (User u : users) {
			if (u.getId().equals(user.getId())) {
				System.out.println("이미 등록된 ID입니다.");
				return ;
			}
		}
		users.add(user);
		System.out.println(user.getName() + "님이 등록되셨습니다.");
	}
	/**
	 * name을 가진 GuestHouse 클래스를 guestHouses ArrayList에서 삭제한다.
	 */
	@Override
	public void deleteGuestHouse(String name) throws RecordNotFoundException{//RecordNotFound
		boolean find = false;
		int idx = 0;
		if (guestHouses.isEmpty()) {
			System.out.println("등록된 게스트하우스가 없습니다.");
			return ;
		}
		for (GuestHouse g : guestHouses) {
			if (g.getName().equals(name)) {
				find = true;
				break;
			}
			idx++;
		}
		if (find == true) {
			guestHouses.remove(idx);
			System.out.println(name+"를 삭제하였습니다.");
			return ;
		}
		System.out.println(name + "은(는) 등록되지 않은 게스트하우스입니다.");
	}
	/**
	 * ID가 id인 User 클래스를 users에서 삭제한다.
	 */
	@Override
	public void deleteUser(String id) throws RecordNotFoundException{//RecordNotFound
		boolean find = false;
		int idx = 0;
		if (users.isEmpty()) {
			System.out.println("등록된 유저가 없습니다.");
			return ;
		}
		for (User u : users) {
			if (u.getId().equals(id)) {
				find = true;
				break;
			}
			idx++;
		}
		if (find == true) {
			users.remove(idx);
			System.out.println(id + "번 ID를 삭제하였습니다.");
			return ;
		}
		System.out.println(id + "은(는) 등록되지 않은 ID입니다.");
	}
	/**
	 * ID가 id인 User클래스를 users에서 찾아내 반환한다.
	 * 없으면 null을 반환한다.
	 */
	@Override
	public User searchUser(String id) {
		User temp = null;
		if (users.isEmpty()) {
			System.out.println("등록된 id가 없습니다.");
			return temp;
		}
		for (User u : users) {
			if (u.getId().equals(id)) {
				temp = u;
				return temp;
			}
		}
		System.out.println("등록되지 않은 id입니다.");
		return temp;
	}
	/**
	 * 등록된 모든 User클래스를 List에 담아 반환한다.
	 */
	@Override
	public List<User> searchAllUsers() {
		if (users.isEmpty()) {
			System.out.println("등록된 사용자가 없습니다.");
		}
		return users;
	}
	/**
	 * 입력된 name과 같은 GuestHouse를 반환한다.
	 * 없으면 null을 반환한다.
	 */
	@Override
	public GuestHouse searchGuestHouse(String name) {
		GuestHouse temp = null;
		if (guestHouses.isEmpty()) {
			System.out.println("등록된 게스트하우스가 없습니다.");
			return temp;
		}
		for (GuestHouse g : guestHouses) {
			if (g.getName().equals(name)) {
				temp = g;
				return temp;
			}
		}
		System.out.println("등록되지 않은 게스트하우스입니다.");
		return temp;
	}
	/**
	 * guestHouses에 등록된 모든 GuestHouse를 List에 담아 반환한다.
	 */
	@Override
	public List<GuestHouse> searchAllGuestHouse() {
		if (guestHouses.isEmpty()) {
			System.out.println("등록된 게스트하우스가 없습니다.");
		}
		return guestHouses;
	}
	/**
	 * user의 id값을 users에 있는 User클래스의 id와 비교하여
	 * 같은 id가 있다면 새로 입력된 user의 정보로 수정한다.
	 * 들어온 User가 상속된 Guest와 Employee라면 형변환 하여 수정한다.
	 */
	@Override
	public void updateUser(User user) throws RecordNotFoundException{//RecordNotFound
		if (users.isEmpty()) {
			System.out.println("등록된 유저가 없습니다.");
			return;
		}
		for (int i=0;i<users.size();i++) {
			if (users.get(i).getId().equals(user.getId())) {
				if (user instanceof Guest) {
					users.set(i, (Guest)user);
					System.out.println(user.getName() + "님의 정보가 수정되었습니다.");
					return ;
				}else if (user instanceof Employee) {
					users.set(i, (Employee)user);
					System.out.println(user.getName() + "님의 정보가 수정되었습니다.");
					return ;
				}
			}
		}
		System.out.println("등록되지 않은 유저입니다.");
	}
	/**
	 * guesthouse의 name과 guestHouses 안의 GuestHouse클래스의 id와 비교하여
	 * 같은 id가 있다면 새로 입력된 guesthouse 정보로 수정한다. 
	 */
	@Override
	public void updateGuestHouse(GuestHouse guesthouse) throws RecordNotFoundException{//RecordNotFound
		if (users.isEmpty()) {
			System.out.println("등록된 유저가 없습니다.");
			return;
		}
		for (int i=0;i<users.size();i++) {
			if (guestHouses.get(i).getName().equals(guesthouse.getName())) {
				guestHouses.set(i, guesthouse);
				System.out.println(guesthouse.getName() + "의 정보가 수정되었습니다.");
				return ;
			}
		}
		System.out.println("등록되지 않은 게스트하우스입니다.");
	}


	private void initRoomStatus(String location) 
	{
		// location이 같고 시간 안에 있는 값.
		GuestHouse g = new GuestHouse();
		for(Booking b:bookings) {
			// current_Date가 date2 라면 startdate.isBefore, endDAte.isAfter
			if(b.getGuesthouse().getAddress().equals(location)) {
				g = b.getGuesthouse();
			}
		}
		for(String key:g.getRooms().keySet()) {
			currentRoomStatus.put(key, 0);
		}
	}
	
	private void checkRoomStatus(String location,LocalDate currentDate) {
		for(Booking b:bookings) {
			// current_Date가 date2 라면 startdate.isBefore, endDAte.isAfter
			if(b.getGuesthouse().getAddress().equals(location)) {
				if((b.getStartDate().getDate().isBefore(currentDate) || b.getStartDate().getDate().isEqual(currentDate))
						&& ( b.getEndDate().getDate().isAfter(currentDate) || b.getStartDate().getDate().isEqual(currentDate))) 
				{
					String address = b.getGuesthouse().getAddress();
					int count = currentRoomStatus.getOrDefault(address, 0);
					currentRoomStatus.put(address, count + 1);
				}
			}
		}
	}
	
	private void clearRoomStatus() {
		currentRoomStatus.clear();
	}
	
	@Override
	public void addBook(Booking booking) {
		// checkMemory(String location,LocalDate currentDate)
		// 날짜 한개와 장소에 대해서 check하는 곳
		Boolean flag = true;
		LocalDate current = booking.getStartDate().getDate();
		// booking 데이터에 대해서 장소에 대한 크기 0 인 room
		while (!current.isAfter(booking.getEndDate().getDate())) {
			initRoomStatus(booking.getGuesthouse().getAddress());
			checkRoomStatus(booking.getGuesthouse().getAddress(),current); // current(날짜) 에 대해서 서울게스트하우스 현재 현황
			if(currentRoomStatus.get(booking.getRoomNumber()) >= booking.getGuesthouse().getRooms().get(booking.getRoomNumber())) {
				flag = false;
				break;
			}
			current = current.plusDays(1);
			clearRoomStatus();
		}
		if(flag == true) {
			isbn++;
			booking.setIsbn(isbn);
			bookings.add(booking);
		}
		else return;	
	}
	// public HashMap<Integer,Book> searchBookByTitle(String title) {
		
	//}
	private boolean isEqualBooking(Booking b,Booking booking) 
	{
		
		if((b.getGuest().getId().equals(booking.getGuest().getId()))
						&& (b.getGuesthouse().getName().equals(booking.getGuesthouse().getName()))
						&& (b.getRoomNumber().equals(booking.getRoomNumber()))
						&& (b.getStartDate().getDate().isEqual(booking.getStartDate().getDate()))
						&& (b.getEndDate().getDate().isEqual(booking.getEndDate().getDate()))) 
		{
			return true;
		}
		else 
			return false;
	}
	
	@Override
	public void cancelBook(Booking booking) {
		Boolean flag = true;
		LocalDate current = booking.getStartDate().getDate();

		while (!current.isAfter(booking.getEndDate().getDate())) {
			initRoomStatus(booking.getGuesthouse().getAddress());
			checkRoomStatus(booking.getGuesthouse().getAddress(), current); // current(날짜) 에 대해서 서울게스트하우스 현재 현황
			if (currentRoomStatus.get(booking.getRoomNumber()) <= 0) {
				flag = false;
				break;
			}
			current = current.plusDays(1);
			clearRoomStatus();
		}
		if (flag == true) {
			int idx = -1;
			int xflag = -1;
			for (Booking b : bookings) {
				idx++;
				if (isEqualBooking(b, booking)) {
					bookings.remove(idx);
					xflag = 1;
					break;
				}
			}
			if(xflag == -1 ) {
				return;
			}
			
		} else
			return;

	}


	@Override
	public void updateBook(Booking booking) {
		
		Booking tmp;
		Boolean delete_flag = true;
		LocalDate current = booking.getStartDate().getDate();
		int flag = -1;
		for(Booking b:bookings) {
			if(isEqualBooking(b,booking)) {
				flag = 1;
				break;
			}
		}
		
		
		
		
		
		
		
		
	}



	@Override
	public List<Booking> getBooks() {
		return bookings;
	}

	@Override
	public List<Booking> getBooks(String guestId) {
		List<Booking> temp = new ArrayList<Booking>();
		for(Booking b:bookings) {
			if(b.getGuest().getId().equals(guestId)) {
				temp.add(b);
			}
		}
		return temp;
	}
////
	@Override
	public Boolean isroomFull() {
		LocalDate today=LocalDate.now(); 
		for(GuestHouse gh:guestHouses) {
			initRoomStatus(gh.getAddress()); 
			checkRoomStatus(gh.getAddress(), today);
			
			for(String roomNo: gh.getRooms().keySet()) {
				int booked = currentRoomStatus.getOrDefault(roomNo, 0);
				int capacity = gh.getRooms().get(roomNo);
				
				if(booked<capacity) {
					clearRoomStatus();
					return false;
				}
			} clearRoomStatus();
		}
		
		return true;
	}

	@Override
	public double getSalesForMonth(int year, int month) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForQuarter(int year, int quarter) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForYear(int year) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForMonth(int year, int month, String guestHouseName) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForQuarter(int year, int quarter, String guestHouseName) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForYear(int year, String guestHouseName) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPeakSeason(int year) {//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

}