package com.pro.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pro.exception.DuplicateException;
import com.pro.exception.InvalidTransactionException;
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
 *///
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
				throw new DuplicateException();
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
				throw new DuplicateException();
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
			throw new RecordNotFoundException(name + "은(는) 등록되지 않은 게스트하우스입니다.");
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
		throw new RecordNotFoundException(name + "은(는) 등록되지 않은 게스트하우스입니다.");
	}
	/**
	 * ID가 id인 User 클래스를 users에서 삭제한다.
	 */
	@Override
	public void deleteUser(String id) throws RecordNotFoundException{//RecordNotFound
		boolean find = false;
		int idx = 0;
		if (users.isEmpty()) {
			throw new RecordNotFoundException(id + "은(는) 등록되지 않은 ID입니다.");
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
		throw new RecordNotFoundException(id + "은(는) 등록되지 않은 ID입니다.");
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
			throw new RecordNotFoundException("등록되지 않은 유저입니다.");
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
		throw new RecordNotFoundException("등록되지 않은 유저입니다.");
	}
	/**
	 * guesthouse의 name과 guestHouses 안의 GuestHouse클래스의 id와 비교하여
	 * 같은 id가 있다면 새로 입력된 guesthouse 정보로 수정한다. 
	 */
	@Override
	public void updateGuestHouse(GuestHouse guesthouse) throws RecordNotFoundException{//RecordNotFound
		if (guestHouses.isEmpty()) {
			throw new RecordNotFoundException("등록되지 않은 게스트하우스입니다.");
		}
		for (int i=0;i<guestHouses.size();i++) {
			if (guestHouses.get(i).getName().equals(guesthouse.getName())) {
				guestHouses.set(i, guesthouse);
				System.out.println(guesthouse.getName() + "의 정보가 수정되었습니다.");
				return ;
			}
		}
		throw new RecordNotFoundException("등록되지 않은 게스트하우스입니다.");
	}


	private void initRoomStatus(String location) {
	    GuestHouse g = null;
	    for (GuestHouse gh : guestHouses) {
	        if (gh.getAddress().equals(location)) {
	            g = gh;
	            break;
	        }
	    }

	    if (g == null) return;

	    currentRoomStatus = new HashMap<>(); // 필드 초기화

	    for (String key : g.getRooms().keySet()) {
	        currentRoomStatus.put(key, 0); // roomNumber별 초기값 0
	    }
	}
	
	// ✅ checkRoomStatus - roomNumber 기준으로 예약 수 누적
	private void checkRoomStatus(String location, LocalDate currentDate) {
	    for (Booking b : bookings) {
	        if (b.getGuesthouse().getAddress().equals(location)) {
	            if ((b.getStartDate().getDate().isBefore(currentDate) || b.getStartDate().getDate().isEqual(currentDate)) &&
	                (b.getEndDate().getDate().isAfter(currentDate) || b.getEndDate().getDate().isEqual(currentDate))) {

	                String roomNo = b.getRoomNumber();
	                int count = currentRoomStatus.getOrDefault(roomNo, 0);
	                currentRoomStatus.put(roomNo, count + 1);
	            }
	        }
	    }
	}
	
	private void clearRoomStatus() {
		currentRoomStatus.clear();
	}
	
	@Override
	public void addBook(Booking booking) {
	    for (Booking b : bookings) {
	        if (isEqualBooking(booking, b)) {
	            // 중복 예약이면 추가 금지
	            System.out.println("중복 예약입니다. 예약이 거부되었습니다.");
	            return;
	        }
	    }

	    boolean isAvailable = true;
	    LocalDate current = booking.getStartDate().getDate();

	    while (!current.isAfter(booking.getEndDate().getDate())) {
	        initRoomStatus(booking.getGuesthouse().getAddress());
	        checkRoomStatus(booking.getGuesthouse().getAddress(), current);

	        int currentBooked = currentRoomStatus.getOrDefault(booking.getRoomNumber(), 0);
	        int roomCapacity = booking.getGuesthouse().getRooms().getOrDefault(booking.getRoomNumber(), 0);

	        if (currentBooked >= roomCapacity) {
	            isAvailable = false;
	            break;
	        }

	        current = current.plusDays(1);
	        clearRoomStatus();
	    }

	    if (isAvailable) {
	        isbn++;
	        booking.setIsbn(isbn);
	        bookings.add(booking);
	        System.out.println("예약이 성공적으로 완료되었습니다.");
	    } else {
	        System.out.println("예약이 실패했습니다. 해당 날짜에 방이 가득 찼습니다.");
	    }
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
		Boolean find = false;
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
			for (Booking b : bookings) {
				idx++;
				if (isEqualBooking(b, booking)) {
					bookings.remove(idx);
					find = true;
					break;
				}
			}
			if(find == false) {
				return;
			}
		}else {
			return;
		}
	}

	@Override
	public void updateBook(int isbn, Booking booking) {
		int idx = -1;
		boolean find = false;
		boolean flag = false;
		LocalDate current = booking.getStartDate().getDate();
		for (Booking b : bookings) {
			idx++;
			if (b.getIsbn() == isbn) {
				find = true;
				break;
			}
		}
		if (find == true) {
			while (!current.isAfter(booking.getEndDate().getDate())) {
				initRoomStatus(booking.getGuesthouse().getAddress());
				checkRoomStatus(booking.getGuesthouse().getAddress(),current); // current(날짜) 에 대해서 서울게스트하우스 현재 현황
				if(currentRoomStatus.get(booking.getRoomNumber()) >= booking.getGuesthouse().getRooms().get(booking.getRoomNumber())) {
					flag = true;
					break;
				}
				current = current.plusDays(1);
				clearRoomStatus();
			}
			if (flag == false) {
				bookings.set(idx, booking);
			}
		}else return;
	}



	@Override
	public List<Booking> getBooks() {
		return bookings;
	}
	
	public Booking getBook(int isbn) {
		Booking findBook = null;
		for(Booking b:bookings) {
			if(b.getIsbn() == isbn) {
				findBook = b;
				break;
			}
		}
		if(findBook == null) {
			System.out.println("해당 예약을 찾을 수 없습니다.");
		}
		return findBook;
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
	public Boolean isroomFull(LocalDate current) { 
		for(GuestHouse gh:guestHouses) {
			initRoomStatus(gh.getAddress()); 
			checkRoomStatus(gh.getAddress(), current);
			
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
	public double getSalesForDay(int month, int day) throws InvalidTransactionException{
		// 월 유효성 검사
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    // 해당 월의 마지막 일을 구해 일 유효성 검사
	    YearMonth ym = YearMonth.of(LocalDate.now().getYear(), month);
	    if (day < 1 || day > ym.lengthOfMonth()) {
	        throw new InvalidTransactionException("잘못된 일입니다: " + day);
	    }

	    LocalDate targetDate = LocalDate.of(ym.getYear(), month, day);
	    double total = 0.0;

	    for (Booking b : bookings) {
	        LocalDate start = b.getStartDate().getDate();
	        LocalDate end   = b.getEndDate().getDate();
	        if (!targetDate.isBefore(start) && !targetDate.isAfter(end)) {
	            total += b.getGuesthouse().getPrice();
	        }
	    }

	    return total;
	}
	@Override
	public double getSalesForMonth(int month) throws InvalidTransactionException{
		 // 월 유효성 검사
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }

	    int year = LocalDate.now().getYear();
	    YearMonth ym = YearMonth.of(year, month);
	    LocalDate monthStart = ym.atDay(1);
	    LocalDate monthEnd   = ym.atEndOfMonth();

	    double totalSales = 0.0;

	    for (Booking b : bookings) {
	        LocalDate start = b.getStartDate().getDate();
	        LocalDate end   = b.getEndDate().getDate();

	        // 예약 기간을 대상 월 기간에 맞춰 잘라냄
	        LocalDate effectiveStart = start.isBefore(monthStart) ? monthStart : start;
	        LocalDate effectiveEnd   = end.isAfter(monthEnd)   ? monthEnd   : end;

	        // 잘라낸 기간이 유효한지 확인
	        if (!effectiveStart.isAfter(effectiveEnd)) {
	            long days = ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;
	            totalSales += days * b.getGuesthouse().getPrice();
	        }
	    }

	    return totalSales;
	}

	@Override
	public double getSalesForDay(int month, int day, String guestHouseName) throws InvalidTransactionException{//InvalidTransactionException
		// 월 유효성 검사
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    // 일 유효성 검사
	    YearMonth ym = YearMonth.of(LocalDate.now().getYear(), month);
	    if (day < 1 || day > ym.lengthOfMonth()) {
	        throw new InvalidTransactionException("잘못된 일입니다: " + day);
	    }
	    // 지점 존재 여부 검사
	    boolean exists = guestHouses.stream()
	        .anyMatch(g -> g.getName().equals(guestHouseName));
	    if (!exists) {
	        throw new InvalidTransactionException("등록되지 않은 지점입니다: " + guestHouseName);
	    }

	    LocalDate targetDate = LocalDate.of(ym.getYear(), month, day);
	    double total = 0.0;

	    for (Booking b : bookings) {
	        if (!b.getGuesthouse().getName().equals(guestHouseName)) {
	            continue;
	        }
	        LocalDate start = b.getStartDate().getDate();
	        LocalDate end   = b.getEndDate().getDate();
	        if (!targetDate.isBefore(start) && !targetDate.isAfter(end)) {
	            total += b.getGuesthouse().getPrice();
	        }
	    }
	    return total;
	}

	@Override
	public double getSalesForQuarter(int month, int weekly, String guestHouseName) throws InvalidTransactionException{//InvalidTransactionException
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSalesForMonth(int month, String guestHouseName) throws InvalidTransactionException{//InvalidTransactionException
		// 현재 연도를 기준으로 조회할 월의 시작·끝일을 계산
	    int year = LocalDate.now().getYear();
	    LocalDate monthStart = LocalDate.of(year, month, 1);
	    LocalDate monthEnd   = monthStart.with(TemporalAdjusters.lastDayOfMonth());
       
	    //월 유효성 검사
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    // 지점 유효성 검사
	    boolean exists = guestHouses.stream()
	        .anyMatch(g -> g.getName().equals(guestHouseName));
	    if (!exists) {
	        throw new InvalidTransactionException("등록되지 않은 지점입니다: " + guestHouseName);
	    }

	    double totalSales = 0.0;
	    for (Booking b : bookings) {
	        // 해당 지점 예약만 계산
	        if (!b.getGuesthouse().getName().equals(guestHouseName)) {
	            continue;
	        }
	        LocalDate start = b.getStartDate().getDate();
	        LocalDate end   = b.getEndDate().getDate();

	        // 조회 월 기간에 맞춰 예약 기간을 자름
	        LocalDate effectiveStart = start.isBefore(monthStart) ? monthStart : start;
	        LocalDate effectiveEnd   = end.isAfter(monthEnd)   ? monthEnd   : end;

	        if (!effectiveStart.isAfter(effectiveEnd)) {
	            long days = ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;
	            totalSales += days * b.getGuesthouse().getPrice();
	        }
	    }
	    return totalSales;
	}
	
	private int[] divisionSeason(LocalDate startDate, LocalDate endDate) {
		int[] divisionWeek = new int[4];
		divisionWeek[0] = endDate.getDayOfMonth() - startDate.getDayOfMonth() + 1;
		divisionWeek[1] = 0;
		divisionWeek[2] = (startDate.getDayOfMonth() / 7);
		divisionWeek[3] = divisionWeek[2] + 1;
		if (startDate.getDayOfMonth() <= 7 && endDate.getDayOfMonth() > 7) {
			divisionWeek[0] = 8 - startDate.getDayOfMonth();
			divisionWeek[1] = endDate.getDayOfMonth() - 7;
		}else if (startDate.getDayOfMonth() <= 14 && endDate.getDayOfMonth() > 14) {
			divisionWeek[0] = 15 - startDate.getDayOfMonth();
			divisionWeek[1] = endDate.getDayOfMonth() - 14;
		}else if (startDate.getDayOfMonth() <= 21 && endDate.getDayOfMonth() > 21) {
			divisionWeek[0] = 22 - startDate.getDayOfMonth();
			divisionWeek[1] = endDate.getDayOfMonth() - 21;
		}else if (startDate.getDayOfMonth() <= 28 && endDate.getDayOfMonth() > 28) {
			divisionWeek[0] = 29 - startDate.getDayOfMonth();
			divisionWeek[1] = endDate.getDayOfMonth() - 28;
		}else if (startDate.getDayOfMonth() <= 31 && endDate.getDayOfMonth() < 7) {
			divisionWeek[0] = 32 - startDate.getDayOfMonth();
			divisionWeek[1] = 7 - endDate.getDayOfMonth();
		}
		return divisionWeek;
	}
	
	@Override
	public int getPeakSeason(int month) throws InvalidTransactionException{//InvalidTransactionException
		if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }

	    int year = LocalDate.now().getYear();
	    LocalDate monthStart = LocalDate.of(year, month, 1);
	    LocalDate monthEnd   = monthStart.with(TemporalAdjusters.lastDayOfMonth());

	    // 주별 매출 합계: 1주(1~7일), 2주(8~14일), ..., 5주(29~말일)
	    double[] weekSales = new double[5];
	    for (int i = 0; i < 5; i++) {
	        LocalDate weekStart = monthStart.plusDays(i * 7L);
	        if (weekStart.isAfter(monthEnd)) break;
	        LocalDate weekEnd = weekStart.plusDays(6);
	        if (weekEnd.isAfter(monthEnd)) weekEnd = monthEnd;

	        for (Booking b : bookings) {
	            LocalDate start = b.getStartDate().getDate();
	            LocalDate end   = b.getEndDate().getDate();
	            // 겹치지 않으면 건너뛰기
	            if (end.isBefore(weekStart) || start.isAfter(weekEnd)) continue;

	            // 겹치는 날짜 계산
	            LocalDate effectiveStart = start.isBefore(weekStart) ? weekStart : start;
	            LocalDate effectiveEnd   = end.isAfter(weekEnd)   ? weekEnd   : end;
	            long days = ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;

	            weekSales[i] += days * b.getGuesthouse().getPrice();
	        }
	    }

	    // 최대 매출을 기록한 주 찾기 (1-based)
	    int peakWeek = 1;
	    double maxSales = weekSales[0];
	    for (int i = 1; i < weekSales.length; i++) {
	        if (weekSales[i] > maxSales) {
	            maxSales = weekSales[i];
	            peakWeek = i + 1;
	        }
	    }

	    return peakWeek;
	}
	@Override
	public double getSalesForWeekly(int month, int weekly) throws InvalidTransactionException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}