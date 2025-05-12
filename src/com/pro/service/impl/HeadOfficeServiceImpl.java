package com.pro.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
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
 * @author LEE TAE RAN, KIM SUN HO, LEE YOON YEOL
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
	 * String : 방이름 Integer : 하루에 묵을 수 있는 최대 인원 수
	 */
	private HashMap<String,Integer> currentRoomStatus;
	
	private static final int MAX_NIGHT = 7;
	
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
	 * @return Service
	 */
	public static HeadOfficeServiceImpl getInstance() {
		return SERVICE;
	}
	//Guest 이름을 기준으로 정렬 
	public static class GuestNameComparator implements Comparator<Guest> {
        @Override
        public int compare(Guest g1, Guest g2) {
            return g1.getName().compareTo(g2.getName());
        }
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


	/**
	 * location을 address필드로 가지고 있는 GuestHouse를 찾아
	 * HashMap을 key, 0으로 초기화 시킨다.
	 * @param location GuestHouse의 Address
	 */
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
	/**
	 * 초기화 된 HashMap를 for문으로 조건이 충족할 때 roomNo에 해당하는 Integer값을 1개 올린다.
	 * @param location 게스트하우스의 Address
	 * @param currentDate 현재 날짜
	 */
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
	/**
	 * roomStatus의 모든 값을 clear한다.
	 */
	private void clearRoomStatus() {
		currentRoomStatus.clear();
	}
	/**
	 * 새로운 booking을 추가한다.
	 */
	@Override
	public void addBook(Booking booking) {
	    for (Booking b : bookings) {
	        if (isEqualBooking(booking, b)) {
	            System.out.println("중복 예약입니다. 예약이 거부되었습니다.");
	            return;
	        }
	    }
	    long daysBetween = ChronoUnit.DAYS.between(booking.getStartDate().getDate(), booking.getEndDate().getDate());
	    if (daysBetween > MAX_NIGHT) {
	    	System.out.println("최대 예약 일 수는 7일입니다.");
	    	return ;
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
	    } else {
	        System.out.println("예약이 실패했습니다. 해당 날짜에 방이 가득 찼습니다.");
	    }
	}
	/**
	 * 같은 Booking인지 확인하는 메서드
	 * @param b 원래 있던 Booking
	 * @param booking 들어올 Booking
	 * @return 있으면 true, 없으면 false
	 */
	private boolean isEqualBooking(Booking b,Booking booking) {
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
	/**
	 * Booking을 bookings에서 지워버리는 메서드
	 */
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
	/**
	 * bookings에 담겨있는 Booking의 정보를 수정하는 메서드
	 */
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
	/**
	 *  bookings의 모든 Bookings를 반환하는 메서드
	 */
	@Override
	public List<Booking> getBooks() {
		return bookings;
	}
	/**
	 * isbn인 Booking의 정보를 반환하는 메서드
	 */
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
	/**
	 * guestId로 입력된 모든 Booking클래스의 정보를 반환한다.
	 */
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
	/**
	 * 그 게스트하우스의 모든 객실이 찼는지 확인한다.
	 */
	@Override
	public Boolean isroomFull(String guestHouseName, LocalDate current) { 
		GuestHouse temp = null;
		for (GuestHouse gh : guestHouses) {
			if (gh.getName().equals(guestHouseName)) {
				temp = gh;
				break;
			}
		}
		if (temp == null) {// recordNotFoundException
			System.out.println("찾으시는 게스트하우스가 없습니다.");
			return false;
		}
		initRoomStatus(temp.getAddress()); 
		checkRoomStatus(temp.getAddress(), current);
		for(String roomNo: temp.getRooms().keySet()) {
			int booked = currentRoomStatus.getOrDefault(roomNo, 0);
			int capacity = temp.getRooms().get(roomNo);		
			if(booked<capacity) {
				clearRoomStatus();
				return false;
			}
			clearRoomStatus();
		}
		return true;
	}
	
	private double salesCaculator(LocalDate startDate, LocalDate endDate, Booking b) {
	    LocalDate start = b.getStartDate().getDate();
	    LocalDate end   = b.getEndDate().getDate();

	    LocalDate effectiveStart = start.isBefore(startDate) ? startDate : start;
	    LocalDate effectiveEnd   = end.isAfter(endDate) ? endDate : end;

	    if (effectiveStart.isAfter(effectiveEnd)) return 0.0;

	    long days = ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;
	    return days * b.getGuesthouse().getPrice();
	}
	
	/**
	 * startDate부터 endDate의 범위의 매출을 계산한다.
	 * @param startDate 시작날짜
	 * @param endDate 종료날짜
	 * @return tatalSales;
	 */
	private double salesInRangeProcess(LocalDate startDate, LocalDate endDate) {
	    double totalSales = 0.0;

	    for (Booking b : bookings) {
	        totalSales += salesCaculator(startDate, endDate, b);
	    }
	    return totalSales;
	}
	
	/**
	 * 입력한 날의 매출을 double로 반환한다.
	 */
	@Override
	public double getSalesForDay(int month, int day) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    YearMonth ym = YearMonth.of(LocalDate.now().getYear(), month);
	    if (day < 1 || day > ym.lengthOfMonth()) {
	        throw new InvalidTransactionException("잘못된 일입니다: " + day);
	    }
	    LocalDate target = LocalDate.of(ym.getYear(), month, day);
	    return salesInRangeProcess(target, target);  // 하루만 계산
	}
	/**
	 * 입력한 달의 매출을 double로 반환한다.
	 */
	@Override
	public double getSalesForMonth(int month) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    int year = LocalDate.now().getYear();
	    YearMonth ym = YearMonth.of(year, month);
	    return salesInRangeProcess(ym.atDay(1), ym.atEndOfMonth());
	}
	/**
	 * 
	 * startDate부터 endDate의 범위의 매출을 계산한다.
	 * @param startDate 시작날짜
	 * @param endDate 종료날짜
	 * @param guestHouseName 게스트하우스 이름
	 * @return totalSales
	 * @throws InvalidTransactionException
	 */
	private double salesInRangeProcess(LocalDate startDate, LocalDate endDate, String guestHouseName) throws InvalidTransactionException {
	    boolean exists = guestHouses.stream()
	        .anyMatch(g -> g.getName().equals(guestHouseName));
	    if (!exists) {
	        throw new InvalidTransactionException("등록되지 않은 지점입니다: " + guestHouseName);
	    }
	    double totalSales = 0.0;
	    for (Booking b : bookings) {
	        if (!b.getGuesthouse().getName().equals(guestHouseName)) {
	            continue;
	        }
	        LocalDate start = b.getStartDate().getDate();
	        LocalDate end   = b.getEndDate().getDate();
	        LocalDate effectiveStart = start.isBefore(startDate) ? startDate : start;
	        LocalDate effectiveEnd   = end.isAfter(endDate) ? endDate : end;
	        if (!effectiveStart.isAfter(effectiveEnd)) {
	            long days = ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;
	            totalSales += days * b.getGuesthouse().getPrice();
	        }
	    }
	    return totalSales;
	}
	
	/**
	 * 특정 게스트하우스의 그 날의 매출을 반환한다.
	 */
	@Override
	public double getSalesForDay(int month, int day, String guestHouseName) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    YearMonth ym = YearMonth.of(LocalDate.now().getYear(), month);
	    if (day < 1 || day > ym.lengthOfMonth()) {
	        throw new InvalidTransactionException("잘못된 일입니다: " + day);
	    }
	    LocalDate targetDate = LocalDate.of(ym.getYear(), month, day);
	    return salesInRangeProcess(targetDate, targetDate, guestHouseName);
	}
	/**
	 * 특정 게스트하우스의 달 매출을 반환한다.
	 */
	@Override
	public double getSalesForMonth(int month, String guestHouseName) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    int year = LocalDate.now().getYear();
	    YearMonth ym = YearMonth.of(year, month);
	    return salesInRangeProcess(ym.atDay(1), ym.atEndOfMonth(), guestHouseName);
	}
	/**
	 * 달에 속한 주 매출을 계산하는 로직
	 * @param year 연도
	 * @param month 월
	 * @return 각 Week의 매출 0~4 (1주차~5주차)
	 * @throws InvalidTransactionException
	 */
	private double[] salesForWeekProcess(int year, int month) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    LocalDate monthStart = LocalDate.of(year, month, 1);
	    LocalDate monthEnd   = monthStart.with(TemporalAdjusters.lastDayOfMonth());
	    double[] weekSales = new double[5];

	    for (int i = 0; i < 5; i++) {
	        LocalDate weekStart = monthStart.plusDays(i * 7L);
	        if (weekStart.isAfter(monthEnd)) break;
	        LocalDate weekEnd = weekStart.plusDays(6);
	        if (weekEnd.isAfter(monthEnd)) weekEnd = monthEnd;
	        for (Booking b : bookings) {
	            weekSales[i] += salesCaculator(weekStart, weekEnd, b);
	        }
	    }
	    return weekSales;
	}
	/**
	*	지정된 월의 주별 매출을 조회한다.
	*/
	@Override
	public double getSalesForWeekly(int year, int month, int weekly) throws InvalidTransactionException {
		double[] weekSales = salesForWeekProcess(year, month);
		return weekSales[weekly - 1];
	}
	/**
	 * 그 달의 가장 높은 매출을 기록한 주를 반환한다.
	 */
	@Override
	public int getPeakSeason(int month) throws InvalidTransactionException{
		if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    int year = LocalDate.now().getYear();
	    
	    double[] weekSales = salesForWeekProcess(year, month);

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
	
	/**
	 * 특정 게스트하우스의 특정 주차의 매출을 반환한다.
	 */
	@Override
	public double getSalesForWeekly(int month, int weekly, String guestHouseName) throws InvalidTransactionException {
	    if (month < 1 || month > 12) {
	        throw new InvalidTransactionException("잘못된 월입니다: " + month);
	    }
	    if (weekly < 1 || weekly > 5) {
	        throw new InvalidTransactionException("잘못된 주차입니다: " + weekly);
	    }
	    LocalDate monthStart = LocalDate.of(LocalDate.now().getYear(), month, 1);
	    LocalDate monthEnd = monthStart.with(TemporalAdjusters.lastDayOfMonth());
	    LocalDate weekStart = monthStart.plusDays((weekly - 1) * 7L);
	    if (weekStart.isAfter(monthEnd)) return 0.0;
	    LocalDate weekEnd = weekStart.plusDays(6);
	    if (weekEnd.isAfter(monthEnd)) weekEnd = monthEnd;
	    return salesInRangeProcess(weekStart, weekEnd, guestHouseName);
	}
	
	public double[] getSalesRankingForWeekly(int month) throws InvalidTransactionException {
		int year = LocalDate.now().getYear();
		return salesForWeekProcess(year, month);
	}
}