package com.pro.service.impl.test;

import java.util.Scanner;

import com.pro.exception.InvalidTransactionException;
import com.pro.service.impl.HeadOfficeServiceImpl;
import com.pro.vo.*;
import com.pro.vo.child.Employee;
import com.pro.vo.child.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeadOfficeServiceImplTest {
    public static void main(String[] args) {
        HeadOfficeServiceImpl service = HeadOfficeServiceImpl.getInstance();
        
        Scanner sc = new Scanner(System.in);
        initData(service);
        System.out.println("========================================");
        System.out.println("메뉴를 선택하세요.");
        System.out.println(" 1. 게스트용  ||  2. 관리자용");
        int tmp = -1;
        int select = sc.nextInt();
        if(select == 1) {
        	while(tmp != 5) {
        		System.out.println(" 1. 회원 등록 || 2. 게스트하우스 예약 || 3. 예약 확인 || 4. 예약 변경 || 5. 종료" );
        		System.out.print("선택 >> ");
        		tmp = sc.nextInt();  // 사용자 입력 받기

                switch (tmp) {
                    case 1:
                        System.out.println("▶ 회원 등록 기능 실행");
                        System.out.println("생성할 회원 ID : ");
                        String id = sc.nextLine();
                        System.out.println("생성할 회원 이름 : ");
                        String name = sc.nextLine();
                        System.out.println("회원 핸드폰 번호 :");
                        String phone = sc.nextLine();
                        System.out.println("회원의 국적 : ");
                        String nationaity = sc.nextLine();
                        try {
                        	service.addUser(new Guest(id, name, phone, nationaity));
                        } catch (Exception e) {
                            System.out.println("회원을 등록 할 수 없습니다.\n " + e.getMessage());
                        }
                        break;
                    case 2:
                    	// 게스트하우스 전체 정보
                        for(GuestHouse g: service.searchAllGuestHouse()) {
                        	System.out.println(g);
                        }
                        System.out.println("회원의 아이디를 입력하세요 : ");
                        String findId = sc.nextLine();
                        User client = (Guest)service.searchUser(findId);
                        System.out.println("게스트하우스 명을 입력하세요 : ");
                        String gName = sc.nextLine();
                        GuestHouse guesthouse = service.searchGuestHouse(gName);
                        System.out.println("예약하고 싶은 방 이름을 입력하세요 : ");
                        String rName = sc.nextLine();
                        System.out.println("게스트하우스 체크인 날짜를 입력하세요 : ");
                        String startdate = sc.nextLine();
                        
                        LocalDate sdate = null;

                        try {
                            sdate = LocalDate.parse(startdate);
                            System.out.println("입력된 날짜는: " + sdate);
                        } catch (Exception e) {
                            System.out.println("❌ 올바른 날짜 형식이 아닙니다. 예: 2020-03-20");
                        }
                        
                        System.out.println("게스트하우스 체크아웃 날짜를 입력하세요 : ");
                        String enddate = sc.nextLine();

                        LocalDate edate = null;
                        try {
                            edate = LocalDate.parse(enddate);
                            System.out.println("입력된 날짜는: " + edate);
                        } catch (Exception e) {
                            System.out.println("❌ 올바른 날짜 형식이 아닙니다. 예: 2020-03-20");
                        }
                        
                        Booking newBooking = new Booking((Guest)client, guesthouse, rName , new BookingDate(sdate),
                				new BookingDate(edate));
                		service.addBook(newBooking);
                        
                        
                        break;
                    case 3:
                        // 예약 확인 로직
                        System.out.println("회원 아이디를 입력해주세요 : ");
                        String guestId = sc.nextLine();
                        for(Booking b : service.getBooks(guestId))
                        {
                        	System.out.println(b);
                        }
                        
                        break;
                    case 4:
                        // 예약 변경 로직
                        System.out.println("회원 아이디를 입력해주세요 : ");
                        String guestIdd = sc.nextLine();
                        for(Booking b : service.getBooks(guestIdd))
                        {
                        	System.out.println(b);
                        }
                        System.out.println("예약 번호를 입력해주세요 : ");
                        int reservationNum = sc.nextInt();
                        // update는 체크인날짜,체크아웃날짜만 바뀐다.
                        System.out.println("언제로 변경하시겠습니까?");
                        System.out.println("게스트하우스 체크인 날짜를 입력하세요 : ");
                        String startdate02 = sc.nextLine();
                        
                        LocalDate sdate02 = null;

                        try {
                            sdate02 = LocalDate.parse(startdate02);
                            System.out.println("입력된 날짜는: " + sdate02);
                        } catch (Exception e) {
                            System.out.println("❌ 올바른 날짜 형식이 아닙니다. 예: 2020-03-20");
                        }
                        
                        System.out.println("게스트하우스 체크아웃 날짜를 입력하세요 : ");
                        String enddate02 = sc.nextLine();

                        LocalDate edate02 = null;
                        try {
                            edate02 = LocalDate.parse(enddate02);
                            System.out.println("입력된 날짜는: " + edate02);
                        } catch (Exception e) {
                            System.out.println("❌ 올바른 날짜 형식이 아닙니다. 예: 2020-03-20");
                        }
                        
                        Booking findBook02 = service.getBook(reservationNum);
                        findBook02.setStartDate(new BookingDate(sdate02));
                        findBook02.setEndDate(new BookingDate(edate02));
                        
                        service.updateBook(reservationNum, findBook02);
                        
                        break;
                    case 5:
                        System.out.println("▶ 종료합니다.");
                        break;
                    default:
                        System.out.println("⚠ 잘못된 입력입니다. 다시 선택하세요.");
                }
        	}
        	
        } else if(select == 2) {
        	while(tmp != 6) {
		    	System.out.println("=====관리자용=======");
		    	System.out.println("1. 직원 등록 | 2. 게스트 하우스 등록 | 3. 전체 게스트 정보(sort) | 4. 매출 정보 | 5. 인기 핫플레이스 | 6. 뒤로가기");System.out.print("선택 >> ");
        		tmp = sc.nextInt();  // 사용자 입력 받기

                switch (tmp) {
                    case 1:
                    	System.out.println("▶ 회원 등록 기능 실행");
                        System.out.println("생성할 회원 ID : ");
                        String id = sc.nextLine();
                        System.out.println("생성할 회원 이름 : ");
                        String name = sc.nextLine();
                        System.out.println("회원 핸드폰 번호 :");
                        String phone = sc.nextLine();
                        System.out.println("회원의 부서 명 : ");
                        String dept = sc.nextLine();
                        System.out.println("회원의 월급은 : ");
                        Double salary = sc.nextDouble();
                        
                        try {
                        	service.addUser(new Employee(id, name, phone, dept,salary,0.0));
                        } catch (Exception e) {
                            System.out.println("직원을 등록 할 수 없습니다.\n " + e.getMessage());
                        }
                        break;
                    case 2:
                    	System.out.println("▶ 게스트하우스 등록 기능 실행");
                    	System.out.println("게스트하우스 이름은?");
                    	String gName01 = sc.nextLine();
                    	System.out.println("게스트하우스 장소는?");
                    	String gAddr01 = sc.nextLine();
                    	System.out.println("게스트하우스 주위 핫플레이스는?");
                    	String hotPlace = sc.nextLine();
                    	System.out.println("게스트하우스 가격은?");
                    	Double price = sc.nextDouble();
                    	
                    	System.out.println("방 번호와 정원을 입력하세요. 입력을 멈추려면 '그만'을 입력하세요.");
	                    int rSelect = -1;
	                    HashMap<String,Integer> roomMap = new HashMap<>();
                    	while(rSelect != 2) {
                    		System.out.println("1. 추가 2. 그만");	
                    		rSelect = sc.nextInt();
		                    if(rSelect == 1) {
		                    	System.out.println("방 번호와 정원을 입력하세요.");
                    			String roomNumber = sc.next();
		                    	int capacity = sc.nextInt();
		                    	roomMap.put(roomNumber, capacity);
		                    } else if(rSelect == 2) {
		                    	System.out.println("종영합니다.");
		                    }
	                    }
                    	//String name, String address, HashMap<String, Integer> rooms, String hotPlace, double price) {
                    	try {
	                    service.addGuestHouse(new GuestHouse(gName01,gAddr01,roomMap,hotPlace,price));
                    	} catch(Exception e) {
                            System.out.println("중복된 게스트하우스가 있습니다.\n " + e.getMessage());
                        }
	                    
	                    break;
                    case 3:

                        break;
                    case 4:
                    	salesInformaion(service);
                        break;
                    case 5:
                        System.out.println("▶ 종료합니다.");
                        break;
                    case 6:
                        System.out.println("▶ 종료합니다.");
                        break;
                    default:
                        System.out.println("⚠ 잘못된 입력입니다. 다시 선택하세요.");
                }
        	}
        	sc.close();
        	return;
        }
        
        sc.close();


        
        // 예약 현황 출력
        // printBookings("[예약 현황 확인]", service);

        // 특정 날짜 만실 여부 확인
        // LocalDate date1 = LocalDate.of(2025, 5, 10);
        // LocalDate date2 = LocalDate.of(2025, 5, 13);
        // System.out.println("[만실 확인] " + date1 + ": " + service.isroomFull(date1));
        // System.out.println("[만실 확인] " + date2 + ": " + service.isroomFull(date2));
    }

    // 방 번호와 수용 인원 정보를 담는 맵을 생성하는 유틸리티 함수
    private static HashMap<String, Integer> createRoomMap(String[] rooms, int[] capacity) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<rooms.length;i++) {
		    map.put(rooms[i], capacity[i]);
        }
        return map;
    }

    private static void printBookings(String title, HeadOfficeServiceImpl service)  {
        System.out.println("\n" + title);
        for (Booking b : service.getBooks()) {
            System.out.println(b);
        }
    }
    private static void salesInformaion(HeadOfficeServiceImpl service)  {
    	Scanner scanner = new Scanner(System.in);
    	int tmp02 = -1;
    	while(tmp02 != 7) {
    		System.out.println("메뉴를 선택 하세요");
    		System.out.println("1. 전체 일간 매출 || 2. 전체 주간 매출 || 3. 전체 월간 매출 || 4. 한 게스트하우스에 대한 일간 매출 || 5. 한 게스트하우스에 대한 주간 매출 || 6. 한 게스트하우스에 대한 월간 매출 || 7. 종료");
    		System.out.print("선택 >> ");
    		tmp02 = scanner.nextInt();
    		

			switch (tmp02) {
			case 1:
				System.out.println("일간 매출을 받고 싶은 날짜를 입력하세요.");
	    		System.out.println("몇 월입니까?");
	    		int month = scanner.nextInt();
	    		System.out.println("몇 일입니까?");
	    		int day = scanner.nextInt();
	    		try {
	    		System.out.println(service.getSalesForDay(month, day));
	    		} catch(InvalidTransactionException e) {
	    			System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
	    		}
	    		break;
			case 2:
				System.out.println("주간 매출을 받고 싶은 날짜를 입력하세요");
				System.out.println("몇 년입니까?");
				int year01 = scanner.nextInt();
				System.out.println("몇 월입니까?");
				int month01 = scanner.nextInt();
				System.out.println("몇 주차 입니까?");
				int week01 = scanner.nextInt();
				try {
					System.out.println(service.getSalesForWeekly(year01, month01, week01));
				}catch (InvalidTransactionException e){
					System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
				}
				break;
			case 3:
				System.out.println("월간 매출을 받고 싶은 날짜를 입력하세요.");
				System.out.println("몇 월입니까?");
	    		int month02 = scanner.nextInt();
	    		try {	
	    			System.out.println(service.getSalesForMonth(month02));
	    		} catch(InvalidTransactionException e) {
	    			System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
	    		}
				break; 
			case 4:
				System.out.println("어떤 게스트하우스를 알고 싶으십니까? 게스트하우스 이름을 입력하세요.");
				String guestHouse = scanner.nextLine();
				System.out.println("월간 매출을 받고 싶은 날짜를 입력하세요.");
				System.out.println("몇 월입니까?");
	    		int month03 = scanner.nextInt();
	    		System.out.println("몇 일입니까?");
	    		int day03 = scanner.nextInt();
	    		try {
	    			System.out.println(service.getSalesForDay(month03, day03,guestHouse));
	    		} catch(InvalidTransactionException e) {
	    			System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
	    		}
				break;
			case 5:
				System.out.println("어떤 게스트하우스를 알고 싶으십니까? 게스트하우스 이름을 입력하세요.");
				String guestHouse05 = scanner.nextLine();
				System.out.println("월간 매출을 받고 싶은 날짜를 입력하세요.");
				System.out.println("몇 월입니까?");
	    		int month05 = scanner.nextInt();
	    		System.out.println("몇 주차입니까?");
	    		int week05 = scanner.nextInt();
	    		try {
	    			System.out.println(service.getSalesForWeekly(month05, week05, guestHouse05));
	    		} catch(InvalidTransactionException e) {
	    			System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
	    		}
				break;
			case 6:
				System.out.println("어떤 게스트하우스를 알고 싶으십니까? 게스트하우스 이름을 입력하세요.");
				String guestHouse02 = scanner.nextLine();
				System.out.println("몇 월입니까?");
	    		int month04 = scanner.nextInt();
	    		try {	
	    			System.out.println(service.getSalesForMonth(month04, guestHouse02));
	    		} catch(InvalidTransactionException e) {
	    			System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
	    		}
				break;
			case 7 :
				System.out.println("어떤 월의 주간 매출 랭킹을 가져오시겠습니까?");
				int month07 = scanner.nextInt();
				List<WeekSalesRanking> weekSalesRanking = new ArrayList<WeekSalesRanking>();
				try {
					double[] weekSales = service.getSalesRankingForWeekly(month07);
					int idx = 0;
					for (double d : weekSales) {
						weekSalesRanking.add(new WeekSalesRanking(idx++, d));
					}
					weekSalesRanking.sort(Comparator.comparingDouble(ws -> -ws.sales));
					System.out.println();
					for (WeekSalesRanking ws : weekSalesRanking) {
						System.out.println(ws);
					}
				} catch(InvalidTransactionException e) {
					System.out.println("제대로 된 값을 입력하지 않으셨습니다. " + e);
				}
			case 8:
	
				System.out.println("▶ 종료합니다.");
				break;
			default:
				System.out.println("⚠ 잘못된 입력입니다. 다시 선택하세요.");
			}

		}
	}
    
    private static void initData(HeadOfficeServiceImpl service) {
    	
    	// ***************************게스트정보********************
    	User emp1 = new Employee("emp001", "김관리", "010-1111-1111", "운영팀", 300, 50);
        User emp2 = new Employee("emp002", "이관리", "010-2222-2222", "기획팀", 310, 40);
        
        
        User guest001 = new Guest("guest001", "김승연", "010-1234-1234", "한국");
        User guest002 = new Guest("guest002", "이태훈", "010-3102-2566", "한국");
        User guest003 = new Guest("guest003", "장재원", "010-6432-2166", "한국");
        User guest004 = new Guest("guest004", "강태구", "010-1732-1274", "한국");
        User guest005 = new Guest("guest005", "강경문", "010-1516-6545", "한국");
        User guest006 = new Guest("guest006", "강경인", "010-3634-9911", "한국");
        User guest007 = new Guest("guest007", "강동석", "010-2523-9812", "한국");
        User guest008 = new Guest("guest008", "강명구", "010-3883-9713", "한국");
        User guest009 = new Guest("guest009", "강보연", "010-2749-9614", "한국");
        User guest010 = new Guest("guest010", "강재현", "010-5927-9515", "한국");
        User guest011 = new Guest("guest011", "강태영", "010-9204-9416", "한국");
        User guest012 = new Guest("guest012", "고대열", "010-5782-9317", "한국");
        User guest013 = new Guest("guest013", "고병선", "010-8933-9218", "한국");
        User guest014 = new Guest("guest014", "고진규", "010-9893-9119", "한국");
        User guest015 = new Guest("guest015", "고현근", "010-9451-9020", "한국");
        User guest016 = new Guest("guest016", "권필성", "010-1925-8921", "한국");
        User guest017 = new Guest("guest017", "권기홍", "010-7332-8822", "한국");
        User guest018 = new Guest("guest018", "권용길", "010-9117-8723", "한국");
        User guest019 = new Guest("guest019", "김재우", "010-0441-8624", "한국");
        User guest020 = new Guest("guest020", "김영주", "010-0622-8525", "한국");
        User guest021 = new Guest("guest021", "김정현", "010-0284-8426", "한국");
        User guest022 = new Guest("guest022", "김가영", "010-0999-8327", "한국");
        User guest023 = new Guest("guest023", "김경아", "010-8487-8228", "한국");
        User guest024 = new Guest("guest024", "김규년", "010-4875-8129", "한국");
        User guest025 = new Guest("guest025", "김기훈", "010-5823-8030", "한국");
        User guest026 = new Guest("guest026", "김대홍", "010-6912-7931", "한국");
        User guest027 = new Guest("guest027", "김동혁", "010-5923-7832", "한국");
        User guest028 = new Guest("guest028", "김민규", "010-2394-7733", "한국");
        User guest029 = new Guest("guest029", "김민우", "010-9384-7634", "한국");
        User guest030 = new Guest("guest030", "김민준", "010-7244-7535", "한국");
        User guest031 = new Guest("guest031", "김석민", "010-5892-7436", "한국");
        User guest032 = new Guest("guest032", "김성헌", "010-1518-7337", "한국");
        User guest033 = new Guest("guest033", "김재영", "010-2123-7238", "한국");
        User guest034 = new Guest("guest034", "김지윤", "010-2428-7139", "한국");
        User guest035 = new Guest("guest035", "김하영", "010-2921-7040", "한국");
        User guest036 = new Guest("guest036", "김학진", "010-3031-6941", "한국");
        User guest037 = new Guest("guest037", "김세화", "010-3132-6842", "한국");
        User guest038 = new Guest("guest038", "류하진", "010-3334-6743", "한국");
        User guest039 = new Guest("guest039", "박민주", "010-3536-6644", "한국");
        User guest040 = new Guest("guest040", "박상민", "010-3738-1755", "한국");
        User guest041 = new Guest("guest041", "박영재", "010-3940-6446", "한국");
        User guest042 = new Guest("guest042", "박요한", "010-4142-6347", "한국");
        User guest043 = new Guest("guest043", "박우창", "010-4243-6248", "한국");
        User guest044 = new Guest("guest044", "박지현", "010-4344-6149", "한국");
        User guest045 = new Guest("guest045", "박우창", "010-4345-6050", "한국");
        User guest046 = new Guest("guest046", "박종빈", "010-4647-5951", "한국");
        User guest047 = new Guest("guest047", "박지환", "010-4849-5852", "한국");
        User guest048 = new Guest("guest048", "배재현", "010-5051-5753", "한국");
        User guest049 = new Guest("guest049", "선민정", "010-5253-5654", "한국");
        User guest050 = new Guest("guest050", "송지원", "010-5455-5599", "한국");
        User guest051 = new Guest("guest051", "송지훈", "010-5657-4667", "한국");
        User guest052 = new Guest("guest052", "신동우", "010-5859-4581", "한국");
        User guest053 = new Guest("guest053", "신주호", "010-6181-6151", "한국");
        
        


        try {
            service.addUser(guest001);
            service.addUser(guest002);
            service.addUser(guest003);
            service.addUser(guest004);
            service.addUser(guest005);
            service.addUser(guest006);
            service.addUser(guest007);
            service.addUser(guest008);
            service.addUser(guest009);
            service.addUser(guest010);
            service.addUser(guest011);
            service.addUser(guest012);
            service.addUser(guest013);
            service.addUser(guest014);
            service.addUser(guest015);
            service.addUser(guest016);
            service.addUser(guest017);
            service.addUser(guest018);
            service.addUser(guest019);
            service.addUser(guest020);
            service.addUser(guest021);
            service.addUser(guest022);
            service.addUser(guest023);
            service.addUser(guest024);
            service.addUser(guest025);
            service.addUser(guest026);
            service.addUser(guest027);
            service.addUser(guest028);
            service.addUser(guest029);
            service.addUser(guest030);
            service.addUser(guest031);
            service.addUser(guest032);
            service.addUser(guest033);
            service.addUser(guest034);
            service.addUser(guest035);
            service.addUser(guest036);
            service.addUser(guest037);
            service.addUser(guest038);
            service.addUser(guest039);
            service.addUser(guest040);
            service.addUser(guest041);
            service.addUser(guest042);
            service.addUser(guest043);
            service.addUser(guest044);
            service.addUser(guest045);
            service.addUser(guest046);
            service.addUser(guest047);
            service.addUser(guest048);
            service.addUser(guest049);
            service.addUser(guest050);
            service.addUser(guest051);
            service.addUser(guest052);
            service.addUser(guest053);
            service.addUser(new Guest("g_001", "Tom Smith", "012-5745-5073", "USA"));
            service.addUser(new Guest("g_002", "Jane Williams", "013-4090-4705", "Brazil"));
			service.addUser(new Guest("g_003", "Chris Williams", "014-2624-8939", "Brazil"));
			service.addUser(new Guest("g_004", "Anna Taylor", "010-6260-5922", "UK"));
			service.addUser(new Guest("g_005", "Alice Jones", "010-5679-7491", "UK"));
			service.addUser(new Guest("g_006", "John Clark", "013-8732-7884", "Australia"));
			service.addUser(new Guest("g_007", "Chris Brown", "012-6617-4753", "Canada"));
			service.addUser(new Guest("g_008", "Jane Miller", "014-1385-7322", "Japan"));
			service.addUser(new Guest("g_009", "John Wilson", "013-5271-4257", "Canada"));
			service.addUser(new Guest("g_010", "Bob Jones", "014-6278-1618", "UK"));
			service.addUser(new Guest("g_011", "Bob Clark", "012-2212-7205", "Canada"));
			service.addUser(new Guest("g_012", "Michael Brown", "011-3012-2038", "Japan"));
			service.addUser(new Guest("g_013", "Jane Miller", "013-2457-7058", "Japan"));
			service.addUser(new Guest("g_014", "Bob Jones", "012-9558-8683", "France"));
			service.addUser(new Guest("g_015", "Chris Smith", "012-6636-3274", "Brazil"));
			service.addUser(new Guest("g_016", "Michael Jones", "013-1800-8600", "UK"));
			service.addUser(new Guest("g_017", "Emily Miller", "013-8468-4654", "France"));
			service.addUser(new Guest("g_018", "Anna Wilson", "010-9708-4591", "Canada"));
			service.addUser(new Guest("g_019", "Jane Jones", "011-8767-5482", "Japan"));
			service.addUser(new Guest("g_020", "Chris Miller", "012-7953-1723", "Australia"));
			service.addUser(new Guest("g_021", "Tom Moore", "012-3662-5401", "Germany"));
			service.addUser(new Guest("g_022", "Anna Brown", "013-7754-7279", "UK"));
			service.addUser(new Guest("g_023", "Chris Williams", "012-3038-2491", "France"));
			service.addUser(new Guest("g_024", "Anna Moore", "012-2602-8220", "UK"));
			service.addUser(new Guest("g_025", "Alice Williams", "013-1162-2008", "Canada"));
			service.addUser(new Guest("g_026", "Alice Moore", "014-2096-7028", "Japan"));
			service.addUser(new Guest("g_027", "John Jones", "010-3999-9442", "Korea"));
			service.addUser(new Guest("g_028", "John Clark", "013-2316-7895", "Canada"));
			service.addUser(new Guest("g_029", "Jane Wilson", "013-6507-2536", "France"));
			service.addUser(new Guest("g_030", "Sarah Davis", "014-5568-4140", "UK"));
			service.addUser(new Guest("g_031", "John Wilson", "011-4420-2260", "Australia"));
			service.addUser(new Guest("g_032", "Michael Williams", "012-2323-2601", "Germany"));
			service.addUser(new Guest("g_033", "Chris Jones", "013-9561-4763", "Korea"));
			service.addUser(new Guest("g_034", "Emily Jones", "014-4943-1424", "France"));
			service.addUser(new Guest("g_035", "Emily Smith", "011-9120-5082", "Korea")); 
			service.addUser(new Guest("g_036", "Bob Wilson", "014-1722-2483", "Canada"));
			service.addUser(new Guest("g_037", "John Smith", "011-4567-4053", "UK"));
			service.addUser(new Guest("g_038", "Michael Wilson", "011-3321-6271", "France"));
			service.addUser(new Guest("g_039", "Bob Brown", "014-1722-2483", "Brazil"));
			service.addUser(new Guest("g_040", "Chris Clark", "011-8939-1104", "Australia"));
			service.addUser(new Guest("g_041", "Sarah Jones", "010-6450-7004", "Korea"));
			service.addUser(new Guest("g_042", "Alice Williams", "013-7721-7008", "Australia"));
			service.addUser(new Guest("g_043", "Chris Miller", "011-9771-5239", "Canada"));
			service.addUser(new Guest("g_044", "John Taylor", "011-5717-8001", "Japan"));
			service.addUser(new Guest("g_045", "Jane Davis", "013-9773-4871", "Germany"));
			service.addUser(new Guest("g_046", "Jane Jones", "013-2385-6295", "Germany"));
			service.addUser(new Guest("g_047", "Emily Davis", "013-7807-2025", "UK"));
			service.addUser(new Guest("g_048", "Jane Jones", "010-8393-4137", "Brazil"));
			service.addUser(new Guest("g_049", "Jane Taylor", "014-9757-3195", "Canada"));
			service.addUser(new Guest("g_050", "Bob Davis", "011-6861-5417", "India"));
			service.addUser(new Guest("G001", "Olivia Johnson",     "010-4823-7591",   "USA"));
	        service.addUser(new Guest("G002", "Liam Smith",         "011-3746-1829",   "Canada"));
	        service.addUser(new Guest("G003", "Emma Williams",      "012-9052-1837",   "UK"));
	        service.addUser(new Guest("G004", "Noah Brown",         "013-5918-2746",   "Australia"));
	        service.addUser(new Guest("G005", "Ava Jones",          "014-6837-2951",   "Germany"));
	        service.addUser(new Guest("G006", "Elijah Garcia",      "010-2749-6053",   "Brazil"));
	        service.addUser(new Guest("G007", "Sophia Martinez",    "011-3905-8271",   "Mexico"));
	        service.addUser(new Guest("G008", "William Rodriguez",  "012-4812-9036",   "Spain"));
	        service.addUser(new Guest("G009", "Isabella Davis",     "013-5729-1038",   "France"));
	        service.addUser(new Guest("G010", "James Miller",       "014-6930-2174",   "Italy"));
	        service.addUser(new Guest("G011", "Mia Wilson",         "010-8092-3765",   "Japan"));
	        service.addUser(new Guest("G012", "Benjamin Moore",     "011-9182-6450",   "China"));
	        service.addUser(new Guest("G013", "Charlotte Taylor",   "012-2751-8390",   "Korea"));
	        service.addUser(new Guest("G014", "Lucas Anderson",     "013-3467-2819",   "Sweden"));
	        service.addUser(new Guest("G015", "Amelia Thomas",      "014-4578-3920",   "Norway"));
	        service.addUser(new Guest("G016", "Mason Jackson",      "010-5689-2041",   "Netherlands"));
	        service.addUser(new Guest("G017", "Harper White",       "011-6790-3152",   "Belgium"));
	        service.addUser(new Guest("G018", "Ethan Harris",       "012-7901-4263",   "Switzerland"));
	        service.addUser(new Guest("G019", "Evelyn Martin",      "013-8012-5374",   "Austria"));
	        service.addUser(new Guest("G020", "Logan Thompson",     "014-9123-6485",   "Portugal"));
	        service.addUser(new Guest("G021", "Abigail Garcia",     "010-1234-0123",   "India"));
	        service.addUser(new Guest("G022", "Jacob Martinez",     "011-2345-1234",   "Russia"));
	        service.addUser(new Guest("G023", "Emily Robinson",     "012-3456-2345",   "Israel"));
	        service.addUser(new Guest("G024", "Michael Clark",      "013-4567-3456",   "Egypt"));
	        service.addUser(new Guest("G025", "Elizabeth Rodriguez","014-5678-4567",  "Argentina"));
	        service.addUser(new Guest("G026", "Alexander Lewis",    "010-6789-5678",   "Chile"));
	        service.addUser(new Guest("G027", "Sofia Lee",          "011-7890-6789",   "South Korea"));
	        service.addUser(new Guest("G028", "Daniel Walker",      "012-8901-7890",   "New Zealand"));
	        service.addUser(new Guest("G029", "Victoria Hall",      "013-9012-8901",   "Ireland"));
	        service.addUser(new Guest("G030", "Matthew Allen",      "014-0123-9012",   "South Africa"));
	        service.addUser(new Guest("G031", "Grace Young",        "010-1357-2468",   "Thailand"));
	        service.addUser(new Guest("G032", "David Hernandez",    "011-2468-3579",   "Vietnam"));
	        service.addUser(new Guest("G033", "Chloe King",         "012-3579-4680",   "Philippines"));
	        service.addUser(new Guest("G034", "Joseph Wright",      "013-4680-5791",   "Singapore"));
	        service.addUser(new Guest("G035", "Lily Scott",         "014-5791-6802",   "Malaysia"));
	        service.addUser(new Guest("G036", "Samuel Green",       "010-6802-7913",   "Indonesia"));
	        service.addUser(new Guest("G037", "Hannah Baker",       "011-7913-8024",   "Greece"));
	        service.addUser(new Guest("G038", "Owen Adams",         "012-8024-9135",   "Turkey"));
	        service.addUser(new Guest("G039", "Ella Nelson",        "013-9135-0246",   "Poland"));
	        service.addUser(new Guest("G040", "Jackson Carter",     "014-0246-1357",   "Czech Republic"));
	        service.addUser(new Guest("G041", "Victoria Mitchell",  "010-2468-3571",   "Hungary"));
	        service.addUser(new Guest("G042", "Sebastian Perez",    "011-3571-4682",   "Romania"));
	        service.addUser(new Guest("G043", "Zoey Roberts",       "012-4682-5793",   "Croatia"));
	        service.addUser(new Guest("G044", "Caleb Turner",       "013-5793-6804",   "Slovakia"));
	        service.addUser(new Guest("G045", "Penelope Phillips",  "014-6804-7915",   "Slovenia"));
	        service.addUser(new Guest("G046", "Nathan Campbell",    "010-7915-8026",   "Estonia"));
	        service.addUser(new Guest("G047", "Scarlett Parker",    "011-8026-9137",   "Latvia"));
	        service.addUser(new Guest("G048", "Christian Evans",    "012-9137-0248",   "Lithuania"));
	        service.addUser(new Guest("G049", "Aria Edwards",       "013-0248-1359",   "Iceland"));
	        service.addUser(new Guest("G050", "Isaac Collins",      "014-1359-2460",   "Luxembourg"));
	        


            

            
        } catch (Exception e) {
            System.out.println("초기 등록 중 예외 발생: " + e.getMessage());
        }
        
        // ************** 게스트하우스 정보 *********************
        
		String[] seoulRooms = { "101", "102", "103", "104", "105", "106", "107" };
		int[] seoulCapacity = { 8, 8, 4, 6, 8, 8, 4 };

		GuestHouse seoul = new GuestHouse("서울하우스", "서울 강남구", createRoomMap(seoulRooms, seoulCapacity), "남산타워", 80);

		String[] parisRooms = { "201", "202", "203", "204" };
		int[] parisCapacity = { 6, 8, 12, 8 };

		GuestHouse paris = new GuestHouse("파리하우스", "37 Rue de la Bucherie, 75005, Paris",
				createRoomMap(parisRooms, parisCapacity), "에펠탑", 120);

		try {
			service.addGuestHouse(seoul);
			service.addGuestHouse(paris);
		} catch (Exception e) {
			System.out.println("초기 등록 중 예외 발생: " + e.getMessage());
		}
        
        
        
        
        //******************************예약정보************************************
		Booking booking1 = new Booking((Guest) guest001, paris, "202", new BookingDate(LocalDate.of(2025, 3, 8)),
				new BookingDate(LocalDate.of(2025, 3, 11)));
		service.addBook(booking1);

		Booking booking2 = new Booking((Guest) guest001, paris, "204", new BookingDate(LocalDate.of(2025, 3, 26)),
				new BookingDate(LocalDate.of(2025, 3, 28)));
		service.addBook(booking2);

		Booking booking3 = new Booking((Guest) guest002, paris, "202", new BookingDate(LocalDate.of(2025, 4, 24)),
				new BookingDate(LocalDate.of(2025, 4, 25)));
		service.addBook(booking3);

		Booking booking4 = new Booking((Guest) guest003, paris, "203", new BookingDate(LocalDate.of(2025, 4, 23)),
				new BookingDate(LocalDate.of(2025, 4, 25)));
		service.addBook(booking4);

		Booking booking5 = new Booking((Guest) guest004, seoul, "106", new BookingDate(LocalDate.of(2025, 4, 13)),
				new BookingDate(LocalDate.of(2025, 4, 15)));
		service.addBook(booking5);

		Booking booking6 = new Booking((Guest) guest005, seoul, "103", new BookingDate(LocalDate.of(2025, 4, 22)),
				new BookingDate(LocalDate.of(2025, 4, 24)));
		service.addBook(booking6);

		Booking booking7 = new Booking((Guest) guest005, paris, "204", new BookingDate(LocalDate.of(2025, 4, 17)),
				new BookingDate(LocalDate.of(2025, 4, 19)));
		service.addBook(booking7);

		Booking booking8 = new Booking((Guest) guest006, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 25)),
				new BookingDate(LocalDate.of(2025, 4, 27)));
		service.addBook(booking8);

		Booking booking9 = new Booking((Guest) guest007, seoul, "101", new BookingDate(LocalDate.of(2025, 3, 14)),
				new BookingDate(LocalDate.of(2025, 3, 16)));
		service.addBook(booking9);

		Booking booking10 = new Booking((Guest) guest008, paris, "204", new BookingDate(LocalDate.of(2025, 4, 23)),
				new BookingDate(LocalDate.of(2025, 4, 25)));
		service.addBook(booking10);

		Booking booking11 = new Booking((Guest) guest009, paris, "202", new BookingDate(LocalDate.of(2025, 3, 28)),
				new BookingDate(LocalDate.of(2025, 3, 31)));
		service.addBook(booking11);

		Booking booking12 = new Booking((Guest) guest010, seoul, "102", new BookingDate(LocalDate.of(2025, 4, 14)),
				new BookingDate(LocalDate.of(2025, 4, 16)));
		service.addBook(booking12);

		Booking booking13 = new Booking((Guest) guest011, seoul, "103", new BookingDate(LocalDate.of(2025, 3, 24)),
				new BookingDate(LocalDate.of(2025, 3, 26)));
		service.addBook(booking13);

		Booking booking14 = new Booking((Guest) guest011, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 4)),
				new BookingDate(LocalDate.of(2025, 4, 6)));
		service.addBook(booking14);

		Booking booking15 = new Booking((Guest) guest012, paris, "201", new BookingDate(LocalDate.of(2025, 4, 20)),
				new BookingDate(LocalDate.of(2025, 4, 21)));
		service.addBook(booking15);

		Booking booking16 = new Booking((Guest) guest012, seoul, "104", new BookingDate(LocalDate.of(2025, 4, 26)),
				new BookingDate(LocalDate.of(2025, 4, 29)));
		service.addBook(booking16);

		Booking booking17 = new Booking((Guest) guest013, paris, "201", new BookingDate(LocalDate.of(2025, 3, 20)),
				new BookingDate(LocalDate.of(2025, 3, 23)));
		service.addBook(booking17);

		Booking booking18 = new Booking((Guest) guest013, paris, "201", new BookingDate(LocalDate.of(2025, 3, 11)),
				new BookingDate(LocalDate.of(2025, 3, 13)));
		service.addBook(booking18);

		Booking booking19 = new Booking((Guest) guest014, seoul, "106", new BookingDate(LocalDate.of(2025, 4, 15)),
				new BookingDate(LocalDate.of(2025, 4, 17)));
		service.addBook(booking19);

		Booking booking20 = new Booking((Guest) guest014, paris, "202", new BookingDate(LocalDate.of(2025, 4, 3)),
				new BookingDate(LocalDate.of(2025, 4, 6)));
		service.addBook(booking20);

		Booking booking21 = new Booking((Guest) guest015, paris, "203", new BookingDate(LocalDate.of(2025, 4, 10)),
				new BookingDate(LocalDate.of(2025, 4, 12)));
		service.addBook(booking21);

		Booking booking22 = new Booking((Guest) guest016, paris, "203", new BookingDate(LocalDate.of(2025, 3, 20)),
				new BookingDate(LocalDate.of(2025, 3, 23)));
		service.addBook(booking22);

		Booking booking23 = new Booking((Guest) guest017, paris, "202", new BookingDate(LocalDate.of(2025, 4, 5)),
				new BookingDate(LocalDate.of(2025, 4, 7)));
		service.addBook(booking23);

		Booking booking24 = new Booking((Guest) guest017, seoul, "105", new BookingDate(LocalDate.of(2025, 3, 6)),
				new BookingDate(LocalDate.of(2025, 3, 8)));
		service.addBook(booking24);

		Booking booking25 = new Booking((Guest) guest018, seoul, "103", new BookingDate(LocalDate.of(2025, 4, 5)),
				new BookingDate(LocalDate.of(2025, 4, 8)));
		service.addBook(booking25);

		Booking booking26 = new Booking((Guest) guest018, seoul, "105", new BookingDate(LocalDate.of(2025, 4, 17)),
				new BookingDate(LocalDate.of(2025, 4, 18)));
		service.addBook(booking26);

		Booking booking27 = new Booking((Guest) guest019, paris, "202", new BookingDate(LocalDate.of(2025, 4, 28)),
				new BookingDate(LocalDate.of(2025, 4, 29)));
		service.addBook(booking27);

		Booking booking28 = new Booking((Guest) guest019, seoul, "106", new BookingDate(LocalDate.of(2025, 3, 19)),
				new BookingDate(LocalDate.of(2025, 3, 22)));
		service.addBook(booking28);

		Booking booking29 = new Booking((Guest) guest020, seoul, "101", new BookingDate(LocalDate.of(2025, 4, 3)),
				new BookingDate(LocalDate.of(2025, 4, 5)));
		service.addBook(booking29);

		Booking booking30 = new Booking((Guest) guest021, seoul, "107", new BookingDate(LocalDate.of(2025, 3, 16)),
				new BookingDate(LocalDate.of(2025, 3, 17)));
		service.addBook(booking30);

		Booking booking31 = new Booking((Guest) guest021, seoul, "101", new BookingDate(LocalDate.of(2025, 3, 28)),
				new BookingDate(LocalDate.of(2025, 3, 31)));
		service.addBook(booking31);

		Booking booking32 = new Booking((Guest) guest022, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 1)),
				new BookingDate(LocalDate.of(2025, 4, 4)));
		service.addBook(booking32);

		Booking booking33 = new Booking((Guest) guest022, paris, "204", new BookingDate(LocalDate.of(2025, 3, 17)),
				new BookingDate(LocalDate.of(2025, 3, 19)));
		service.addBook(booking33);

		Booking booking34 = new Booking((Guest) guest023, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 20)),
				new BookingDate(LocalDate.of(2025, 4, 23)));
		service.addBook(booking34);

		Booking booking35 = new Booking((Guest) guest024, paris, "203", new BookingDate(LocalDate.of(2025, 4, 7)),
				new BookingDate(LocalDate.of(2025, 4, 9)));
		service.addBook(booking35);

		Booking booking36 = new Booking((Guest) guest024, seoul, "103", new BookingDate(LocalDate.of(2025, 4, 26)),
				new BookingDate(LocalDate.of(2025, 4, 28)));
		service.addBook(booking36);

		Booking booking37 = new Booking((Guest) guest025, paris, "201", new BookingDate(LocalDate.of(2025, 3, 5)),
				new BookingDate(LocalDate.of(2025, 3, 8)));
		service.addBook(booking37);

		Booking booking38 = new Booking((Guest) guest025, paris, "203", new BookingDate(LocalDate.of(2025, 4, 15)),
				new BookingDate(LocalDate.of(2025, 4, 16)));
		service.addBook(booking38);

		Booking booking39 = new Booking((Guest) guest026, seoul, "105", new BookingDate(LocalDate.of(2025, 3, 23)),
				new BookingDate(LocalDate.of(2025, 3, 25)));
		service.addBook(booking39);

		Booking booking40 = new Booking((Guest) guest027, seoul, "105", new BookingDate(LocalDate.of(2025, 3, 15)),
				new BookingDate(LocalDate.of(2025, 3, 17)));
		service.addBook(booking40);

		Booking booking41 = new Booking((Guest) guest028, seoul, "104", new BookingDate(LocalDate.of(2025, 4, 23)),
				new BookingDate(LocalDate.of(2025, 4, 26)));
		service.addBook(booking41);

		Booking booking42 = new Booking((Guest) guest028, seoul, "107", new BookingDate(LocalDate.of(2025, 3, 18)),
				new BookingDate(LocalDate.of(2025, 3, 19)));
		service.addBook(booking42);

		Booking booking43 = new Booking((Guest) guest029, seoul, "104", new BookingDate(LocalDate.of(2025, 4, 1)),
				new BookingDate(LocalDate.of(2025, 4, 3)));
		service.addBook(booking43);

		Booking booking44 = new Booking((Guest) guest029, paris, "204", new BookingDate(LocalDate.of(2025, 3, 4)),
				new BookingDate(LocalDate.of(2025, 3, 5)));
		service.addBook(booking44);

		Booking booking45 = new Booking((Guest) guest030, paris, "203", new BookingDate(LocalDate.of(2025, 3, 15)),
				new BookingDate(LocalDate.of(2025, 3, 16)));
		service.addBook(booking45);

		Booking booking46 = new Booking((Guest) guest030, seoul, "103", new BookingDate(LocalDate.of(2025, 3, 17)),
				new BookingDate(LocalDate.of(2025, 3, 19)));
		service.addBook(booking46);

		Booking booking47 = new Booking((Guest) guest031, paris, "202", new BookingDate(LocalDate.of(2025, 3, 3)),
				new BookingDate(LocalDate.of(2025, 3, 5)));
		service.addBook(booking47);

		Booking booking48 = new Booking((Guest) guest032, seoul, "105", new BookingDate(LocalDate.of(2025, 4, 15)),
				new BookingDate(LocalDate.of(2025, 4, 18)));
		service.addBook(booking48);

		Booking booking49 = new Booking((Guest) guest033, paris, "201", new BookingDate(LocalDate.of(2025, 4, 28)),
				new BookingDate(LocalDate.of(2025, 5, 1)));
		service.addBook(booking49);

		Booking booking50 = new Booking((Guest) guest034, seoul, "106", new BookingDate(LocalDate.of(2025, 4, 19)),
				new BookingDate(LocalDate.of(2025, 4, 21)));
		service.addBook(booking50);

		Booking booking51 = new Booking((Guest) guest035, paris, "202", new BookingDate(LocalDate.of(2025, 3, 4)),
				new BookingDate(LocalDate.of(2025, 3, 7)));
		service.addBook(booking51);

		Booking booking52 = new Booking((Guest) guest036, seoul, "105", new BookingDate(LocalDate.of(2025, 4, 23)),
				new BookingDate(LocalDate.of(2025, 4, 24)));
		service.addBook(booking52);

		Booking booking53 = new Booking((Guest) guest036, seoul, "104", new BookingDate(LocalDate.of(2025, 3, 29)),
				new BookingDate(LocalDate.of(2025, 4, 1)));
		service.addBook(booking53);

		Booking booking54 = new Booking((Guest) guest037, paris, "202", new BookingDate(LocalDate.of(2025, 3, 22)),
				new BookingDate(LocalDate.of(2025, 3, 23)));
		service.addBook(booking54);

		Booking booking55 = new Booking((Guest) guest037, seoul, "103", new BookingDate(LocalDate.of(2025, 4, 14)),
				new BookingDate(LocalDate.of(2025, 4, 17)));
		service.addBook(booking55);

		Booking booking56 = new Booking((Guest) guest038, seoul, "107", new BookingDate(LocalDate.of(2025, 3, 13)),
				new BookingDate(LocalDate.of(2025, 3, 14)));
		service.addBook(booking56);

		Booking booking57 = new Booking((Guest) guest039, seoul, "101", new BookingDate(LocalDate.of(2025, 3, 24)),
				new BookingDate(LocalDate.of(2025, 3, 26)));
		service.addBook(booking57);

		Booking booking58 = new Booking((Guest) guest040, seoul, "102", new BookingDate(LocalDate.of(2025, 4, 9)),
				new BookingDate(LocalDate.of(2025, 4, 10)));
		service.addBook(booking58);

		Booking booking59 = new Booking((Guest) guest040, paris, "204", new BookingDate(LocalDate.of(2025, 3, 14)),
				new BookingDate(LocalDate.of(2025, 3, 16)));
		service.addBook(booking59);

		Booking booking60 = new Booking((Guest) guest041, paris, "202", new BookingDate(LocalDate.of(2025, 4, 9)),
				new BookingDate(LocalDate.of(2025, 4, 11)));
		service.addBook(booking60);

		Booking booking61 = new Booking((Guest) guest041, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 2)),
				new BookingDate(LocalDate.of(2025, 4, 3)));
		service.addBook(booking61);

		Booking booking62 = new Booking((Guest) guest042, seoul, "106", new BookingDate(LocalDate.of(2025, 4, 9)),
				new BookingDate(LocalDate.of(2025, 4, 10)));
		service.addBook(booking62);

		Booking booking63 = new Booking((Guest) guest043, paris, "202", new BookingDate(LocalDate.of(2025, 3, 10)),
				new BookingDate(LocalDate.of(2025, 3, 12)));
		service.addBook(booking63);

		Booking booking64 = new Booking((Guest) guest043, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 24)),
				new BookingDate(LocalDate.of(2025, 4, 26)));
		service.addBook(booking64);

		Booking booking65 = new Booking((Guest) guest044, seoul, "106", new BookingDate(LocalDate.of(2025, 3, 16)),
				new BookingDate(LocalDate.of(2025, 3, 18)));
		service.addBook(booking65);

		Booking booking66 = new Booking((Guest) guest045, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 21)),
				new BookingDate(LocalDate.of(2025, 4, 24)));
		service.addBook(booking66);

		Booking booking67 = new Booking((Guest) guest046, seoul, "102", new BookingDate(LocalDate.of(2025, 3, 20)),
				new BookingDate(LocalDate.of(2025, 3, 23)));
		service.addBook(booking67);

		Booking booking68 = new Booking((Guest) guest046, seoul, "102", new BookingDate(LocalDate.of(2025, 4, 11)),
				new BookingDate(LocalDate.of(2025, 4, 12)));
		service.addBook(booking68);

		Booking booking69 = new Booking((Guest) guest047, paris, "201", new BookingDate(LocalDate.of(2025, 3, 26)),
				new BookingDate(LocalDate.of(2025, 3, 27)));
		service.addBook(booking69);

		Booking booking70 = new Booking((Guest) guest048, seoul, "102", new BookingDate(LocalDate.of(2025, 3, 28)),
				new BookingDate(LocalDate.of(2025, 3, 31)));
		service.addBook(booking70);

		Booking booking71 = new Booking((Guest) guest048, seoul, "104", new BookingDate(LocalDate.of(2025, 3, 26)),
				new BookingDate(LocalDate.of(2025, 3, 29)));
		service.addBook(booking71);

		Booking booking72 = new Booking((Guest) guest049, seoul, "107", new BookingDate(LocalDate.of(2025, 3, 26)),
				new BookingDate(LocalDate.of(2025, 3, 27)));
		service.addBook(booking72);

		Booking booking73 = new Booking((Guest) guest049, seoul, "106", new BookingDate(LocalDate.of(2025, 4, 14)),
				new BookingDate(LocalDate.of(2025, 4, 15)));
		service.addBook(booking73);

		Booking booking74 = new Booking((Guest) guest050, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 16)),
				new BookingDate(LocalDate.of(2025, 4, 17)));
		service.addBook(booking74);

		Booking booking75 = new Booking((Guest) guest051, seoul, "102", new BookingDate(LocalDate.of(2025, 3, 17)),
				new BookingDate(LocalDate.of(2025, 3, 20)));
		service.addBook(booking75);
		Booking booking76 = new Booking((Guest) guest051, paris, "203", new BookingDate(LocalDate.of(2025, 4, 3)),
				new BookingDate(LocalDate.of(2025, 4, 6)));
		service.addBook(booking76);

		Booking booking77 = new Booking((Guest) guest052, seoul, "102", new BookingDate(LocalDate.of(2025, 3, 31)),
				new BookingDate(LocalDate.of(2025, 4, 1)));
		service.addBook(booking77);

		Booking booking78 = new Booking((Guest) guest053, paris, "202", new BookingDate(LocalDate.of(2025, 3, 28)),
				new BookingDate(LocalDate.of(2025, 3, 30)));
		service.addBook(booking78);

		Booking booking79 = new Booking((Guest) guest053, seoul, "107", new BookingDate(LocalDate.of(2025, 4, 21)),
				new BookingDate(LocalDate.of(2025, 4, 24)));
		service.addBook(booking79);

    
	    service.addBook(new Booking((Guest)service.searchUser("g_021"), paris, "201",new BookingDate(LocalDate.of(2025, 3, 1)), new BookingDate(LocalDate.of(2025, 3, 3))));
	    service.addBook(new Booking((Guest)service.searchUser("g_022"), paris, "202", new BookingDate(LocalDate.of(2025, 3, 5)), new BookingDate(LocalDate.of(2025, 3, 7))));
	    service.addBook(new Booking((Guest)service.searchUser("g_023"), paris, "203", new BookingDate(LocalDate.of(2025, 3, 8)), new BookingDate(LocalDate.of(2025, 3, 10))));
	    service.addBook(new Booking((Guest)service.searchUser("g_024"), paris, "204", new BookingDate(LocalDate.of(2025, 3, 12)), new BookingDate(LocalDate.of(2025, 3, 14))));
	    service.addBook(new Booking((Guest)service.searchUser("g_025"), paris, "201", new BookingDate(LocalDate.of(2025, 3, 14)), new BookingDate(LocalDate.of(2025, 3, 16))));
	    service.addBook(new Booking((Guest)service.searchUser("g_026"), paris, "202", new BookingDate(LocalDate.of(2025, 3, 17)), new BookingDate(LocalDate.of(2025, 3, 19))));
	    service.addBook(new Booking((Guest)service.searchUser("g_027"), paris, "203", new BookingDate(LocalDate.of(2025, 3, 20)), new BookingDate(LocalDate.of(2025, 3, 22))));
	    service.addBook(new Booking((Guest)service.searchUser("g_028"), paris, "204", new BookingDate(LocalDate.of(2025, 3, 22)), new BookingDate(LocalDate.of(2025, 3, 24))));
	    service.addBook(new Booking((Guest)service.searchUser("g_029"), paris, "201", new BookingDate(LocalDate.of(2025, 3, 25)), new BookingDate(LocalDate.of(2025, 3, 27))));
	    service.addBook(new Booking((Guest)service.searchUser("g_030"), paris, "202", new BookingDate(LocalDate.of(2025, 3, 28)), new BookingDate(LocalDate.of(2025, 3, 30))));
	    service.addBook(new Booking((Guest)service.searchUser("g_031"), paris, "203", new BookingDate(LocalDate.of(2025, 3, 31)), new BookingDate(LocalDate.of(2025, 4, 2))));
	    service.addBook(new Booking((Guest)service.searchUser("g_032"), paris, "204", new BookingDate(LocalDate.of(2025, 4, 2)), new BookingDate(LocalDate.of(2025, 4, 4))));
	    service.addBook(new Booking((Guest)service.searchUser("g_033"), paris, "201", new BookingDate(LocalDate.of(2025, 4, 4)), new BookingDate(LocalDate.of(2025, 4, 6))));
	    service.addBook(new Booking((Guest)service.searchUser("g_034"), paris, "202", new BookingDate(LocalDate.of(2025, 4, 7)), new BookingDate(LocalDate.of(2025, 4, 9))));
	    service.addBook(new Booking((Guest)service.searchUser("g_035"), paris, "203", new BookingDate(LocalDate.of(2025, 4, 9)), new BookingDate(LocalDate.of(2025, 4, 11))));
	    service.addBook(new Booking((Guest)service.searchUser("g_036"), paris, "204", new BookingDate(LocalDate.of(2025, 4, 12)), new BookingDate(LocalDate.of(2025, 4, 14))));
	    service.addBook(new Booking((Guest)service.searchUser("g_037"), paris, "201", new BookingDate(LocalDate.of(2025, 4, 14)), new BookingDate(LocalDate.of(2025, 4, 16))));
	    service.addBook(new Booking((Guest)service.searchUser("g_038"), paris, "202", new BookingDate(LocalDate.of(2025, 4, 17)), new BookingDate(LocalDate.of(2025, 4, 19))));
	    service.addBook(new Booking((Guest)service.searchUser("g_039"), paris, "203", new BookingDate(LocalDate.of(2025, 4, 20)), new BookingDate(LocalDate.of(2025, 4, 22))));
	    service.addBook(new Booking((Guest)service.searchUser("g_040"), paris, "204", new BookingDate(LocalDate.of(2025, 4, 22)), new BookingDate(LocalDate.of(2025, 4, 24))));
	    service.addBook(new Booking((Guest)service.searchUser("g_041"), paris, "201", new BookingDate(LocalDate.of(2025, 4, 24)), new BookingDate(LocalDate.of(2025, 4, 26))));
	    service.addBook(new Booking((Guest)service.searchUser("g_042"), paris, "202", new BookingDate(LocalDate.of(2025, 4, 26)), new BookingDate(LocalDate.of(2025, 4, 28))));
	    service.addBook(new Booking((Guest)service.searchUser("g_043"), paris, "203", new BookingDate(LocalDate.of(2025, 4, 28)), new BookingDate(LocalDate.of(2025, 4, 30))));
	    service.addBook(new Booking((Guest)service.searchUser("g_044"), paris, "204", new BookingDate(LocalDate.of(2025, 3, 1)), new BookingDate(LocalDate.of(2025, 3, 3))));
	    service.addBook(new Booking((Guest)service.searchUser("g_045"), paris, "201", new BookingDate(LocalDate.of(2025, 3, 2)), new BookingDate(LocalDate.of(2025, 3, 4))));
	    service.addBook(new Booking((Guest)service.searchUser("g_046"), paris, "202", new BookingDate(LocalDate.of(2025, 3, 5)), new BookingDate(LocalDate.of(2025, 3, 7))));
	    service.addBook(new Booking((Guest)service.searchUser("g_047"), paris, "203", new BookingDate(LocalDate.of(2025, 3, 6)), new BookingDate(LocalDate.of(2025, 3, 8))));
	    service.addBook(new Booking((Guest)service.searchUser("g_048"), paris, "204", new BookingDate(LocalDate.of(2025, 3, 9)), new BookingDate(LocalDate.of(2025, 3, 11))));
	    service.addBook(new Booking((Guest)service.searchUser("g_049"), paris, "201", new BookingDate(LocalDate.of(2025, 3, 12)), new BookingDate(LocalDate.of(2025, 3, 14))));
	    service.addBook(new Booking((Guest)service.searchUser("g_050"), seoul, "104", new BookingDate(LocalDate.of(2025, 3, 15)), new BookingDate(LocalDate.of(2025, 3, 17))));
	    service.addBook(new Booking((Guest)service.searchUser("g_001"), seoul, "101", new BookingDate(LocalDate.of(2025, 3, 1)), new BookingDate(LocalDate.of(2025, 3, 3))));
	    service.addBook(new Booking((Guest)service.searchUser("g_002"), seoul, "102", new BookingDate(LocalDate.of(2025, 3, 2)), new BookingDate(LocalDate.of(2025, 3, 5))));
	    service.addBook(new Booking((Guest)service.searchUser("g_003"), seoul, "107", new BookingDate(LocalDate.of(2025, 3, 5)), new BookingDate(LocalDate.of(2025, 3, 8))));
	    service.addBook(new Booking((Guest)service.searchUser("g_004"), seoul, "104", new BookingDate(LocalDate.of(2025, 3, 6)), new BookingDate(LocalDate.of(2025, 3, 9))));
	    service.addBook(new Booking((Guest)service.searchUser("g_005"), seoul, "106", new BookingDate(LocalDate.of(2025, 3, 10)), new BookingDate(LocalDate.of(2025, 3, 13))));
	    service.addBook(new Booking((Guest)service.searchUser("g_006"), seoul, "102", new BookingDate(LocalDate.of(2025, 3, 11)), new BookingDate(LocalDate.of(2025, 3, 14))));
	    service.addBook(new Booking((Guest)service.searchUser("g_007"), seoul, "105", new BookingDate(LocalDate.of(2025, 3, 14)), new BookingDate(LocalDate.of(2025, 3, 17))));
	    service.addBook(new Booking((Guest)service.searchUser("g_008"), seoul, "104", new BookingDate(LocalDate.of(2025, 3, 16)), new BookingDate(LocalDate.of(2025, 3, 19))));
	    service.addBook(new Booking((Guest)service.searchUser("g_009"), seoul, "101", new BookingDate(LocalDate.of(2025, 3, 20)), new BookingDate(LocalDate.of(2025, 3, 23))));
	    service.addBook(new Booking((Guest)service.searchUser("g_010"), seoul, "102", new BookingDate(LocalDate.of(2025, 3, 22)), new BookingDate(LocalDate.of(2025, 3, 25))));
	    service.addBook(new Booking((Guest)service.searchUser("g_011"), seoul, "107", new BookingDate(LocalDate.of(2025, 4, 1)), new BookingDate(LocalDate.of(2025, 4, 4))));
	    service.addBook(new Booking((Guest)service.searchUser("g_012"), seoul, "104", new BookingDate(LocalDate.of(2025, 4, 3)), new BookingDate(LocalDate.of(2025, 4, 6))));
	    service.addBook(new Booking((Guest)service.searchUser("g_013"), seoul, "101", new BookingDate(LocalDate.of(2025, 4, 5)), new BookingDate(LocalDate.of(2025, 4, 8))));
	    service.addBook(new Booking((Guest)service.searchUser("g_014"), seoul, "105", new BookingDate(LocalDate.of(2025, 4, 7)), new BookingDate(LocalDate.of(2025, 4, 10))));
	    service.addBook(new Booking((Guest)service.searchUser("g_015"), seoul, "103", new BookingDate(LocalDate.of(2025, 4, 10)), new BookingDate(LocalDate.of(2025, 4, 13))));
	    service.addBook(new Booking((Guest)service.searchUser("g_016"), seoul, "104", new BookingDate(LocalDate.of(2025, 4, 12)), new BookingDate(LocalDate.of(2025, 4, 15))));
	    service.addBook(new Booking((Guest)service.searchUser("g_017"), seoul, "105", new BookingDate(LocalDate.of(2025, 4, 16)), new BookingDate(LocalDate.of(2025, 4, 19))));
	    service.addBook(new Booking((Guest)service.searchUser("g_018"), seoul, "102", new BookingDate(LocalDate.of(2025, 4, 18)), new BookingDate(LocalDate.of(2025, 4, 21))));
	    service.addBook(new Booking((Guest)service.searchUser("g_019"), seoul, "106", new BookingDate(LocalDate.of(2025, 4, 22)), new BookingDate(LocalDate.of(2025, 4, 25))));
	    service.addBook(new Booking((Guest)service.searchUser("g_020"), seoul, "104", new BookingDate(LocalDate.of(2025, 4, 25)), new BookingDate(LocalDate.of(2025, 4, 28))));
	
	    service.addBook(new Booking((Guest)service.searchUser("G021"), paris, "201", new BookingDate(LocalDate.of(2025, 3, 2)),  new BookingDate(LocalDate.of(2025, 3, 6))));
	        service.addBook(new Booking((Guest)service.searchUser("G022"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 3, 7)),  new BookingDate(LocalDate.of(2025, 3, 8))));
	        service.addBook(new Booking((Guest)service.searchUser("G023"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 3, 10)), new BookingDate(LocalDate.of(2025, 3, 13))));
	        service.addBook(new Booking((Guest)service.searchUser("G024"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 3, 14)), new BookingDate(LocalDate.of(2025, 3, 16))));
	        service.addBook(new Booking((Guest)service.searchUser("G025"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 3, 18)), new BookingDate(LocalDate.of(2025, 3, 21))));
	        service.addBook(new Booking((Guest)service.searchUser("G026"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 3, 22)), new BookingDate(LocalDate.of(2025, 3, 24))));
	        service.addBook(new Booking((Guest)service.searchUser("G027"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 3, 25)), new BookingDate(LocalDate.of(2025, 3, 29))));
	        service.addBook(new Booking((Guest)service.searchUser("G028"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 3, 28)), new BookingDate(LocalDate.of(2025, 3, 30))));
	        service.addBook(new Booking((Guest)service.searchUser("G029"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 3, 30)), new BookingDate(LocalDate.of(2025, 4, 2))));
	        service.addBook(new Booking((Guest)service.searchUser("G030"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 4, 1)),  new BookingDate(LocalDate.of(2025, 4, 4))));
	        service.addBook(new Booking((Guest)service.searchUser("G031"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 4, 5)),  new BookingDate(LocalDate.of(2025, 4, 8))));
	        service.addBook(new Booking((Guest)service.searchUser("G032"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 4, 7)),  new BookingDate(LocalDate.of(2025, 4, 9))));
	        service.addBook(new Booking((Guest)service.searchUser("G033"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 4, 10)), new BookingDate(LocalDate.of(2025, 4, 13))));
	        service.addBook(new Booking((Guest)service.searchUser("G034"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 4, 12)), new BookingDate(LocalDate.of(2025, 4, 14))));
	        service.addBook(new Booking((Guest)service.searchUser("G035"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 4, 14)), new BookingDate(LocalDate.of(2025, 4, 19))));
	        service.addBook(new Booking((Guest)service.searchUser("G036"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 4, 17)), new BookingDate(LocalDate.of(2025, 4, 20))));
	        service.addBook(new Booking((Guest)service.searchUser("G037"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 4, 19)), new BookingDate(LocalDate.of(2025, 4, 23))));
	        service.addBook(new Booking((Guest)service.searchUser("G038"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 4, 21)), new BookingDate(LocalDate.of(2025, 4, 25))));
	        service.addBook(new Booking((Guest)service.searchUser("G039"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 4, 23)), new BookingDate(LocalDate.of(2025, 4, 26))));
	        service.addBook(new Booking((Guest)service.searchUser("G040"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 4, 25)), new BookingDate(LocalDate.of(2025, 4, 29))));
	        service.addBook(new Booking((Guest)service.searchUser("G041"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 3, 3)),  new BookingDate(LocalDate.of(2025, 3, 5))));
	        service.addBook(new Booking((Guest)service.searchUser("G042"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 3, 6)),  new BookingDate(LocalDate.of(2025, 3, 9))));
	        service.addBook(new Booking((Guest)service.searchUser("G043"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 3, 8)),  new BookingDate(LocalDate.of(2025, 3, 10))));
	        service.addBook(new Booking((Guest)service.searchUser("G044"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 3, 11)), new BookingDate(LocalDate.of(2025, 3, 14))));
	        service.addBook(new Booking((Guest)service.searchUser("G045"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 3, 13)), new BookingDate(LocalDate.of(2025, 3, 15))));
	        service.addBook(new Booking((Guest)service.searchUser("G046"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 3, 16)), new BookingDate(LocalDate.of(2025, 3, 18))));
	        service.addBook(new Booking((Guest)service.searchUser("G047"), paris, "203",
	            new BookingDate(LocalDate.of(2025, 3, 19)), new BookingDate(LocalDate.of(2025, 3, 22))));
	        service.addBook(new Booking((Guest)service.searchUser("G048"), paris, "204",
	            new BookingDate(LocalDate.of(2025, 3, 21)), new BookingDate(LocalDate.of(2025, 3, 23))));
	        service.addBook(new Booking((Guest)service.searchUser("G049"), paris, "201",
	            new BookingDate(LocalDate.of(2025, 3, 24)), new BookingDate(LocalDate.of(2025, 3, 26))));
	        service.addBook(new Booking((Guest)service.searchUser("G050"), paris, "202",
	            new BookingDate(LocalDate.of(2025, 3, 27)), new BookingDate(LocalDate.of(2025, 3, 31))));
	            
	        // 3월·4월 서울게스트하우스 예약 (G001 ~ G020)
	        service.addBook(new Booking((Guest)service.searchUser("G001"), seoul, "101",
	            new BookingDate(LocalDate.of(2025, 3, 2)),  new BookingDate(LocalDate.of(2025, 3, 3))));
	        service.addBook(new Booking((Guest)service.searchUser("G002"), seoul, "102",
	            new BookingDate(LocalDate.of(2025, 3, 4)),  new BookingDate(LocalDate.of(2025, 3, 6))));
	        service.addBook(new Booking((Guest)service.searchUser("G003"), seoul, "103",
	            new BookingDate(LocalDate.of(2025, 3, 7)),  new BookingDate(LocalDate.of(2025, 3, 10))));
	        service.addBook(new Booking((Guest)service.searchUser("G004"), seoul, "104",
	            new BookingDate(LocalDate.of(2025, 3, 9)),  new BookingDate(LocalDate.of(2025, 3, 12))));
	        service.addBook(new Booking((Guest)service.searchUser("G005"), seoul, "101",
	            new BookingDate(LocalDate.of(2025, 3, 11)), new BookingDate(LocalDate.of(2025, 3, 14))));
	        service.addBook(new Booking((Guest)service.searchUser("G006"), seoul, "102",
	            new BookingDate(LocalDate.of(2025, 3, 13)), new BookingDate(LocalDate.of(2025, 3, 17))));
	        service.addBook(new Booking((Guest)service.searchUser("G007"), seoul, "103",
	            new BookingDate(LocalDate.of(2025, 3, 15)), new BookingDate(LocalDate.of(2025, 3, 18))));
	        service.addBook(new Booking((Guest)service.searchUser("G008"), seoul, "104",
	            new BookingDate(LocalDate.of(2025, 3, 17)), new BookingDate(LocalDate.of(2025, 3, 20))));
	        service.addBook(new Booking((Guest)service.searchUser("G009"), seoul, "105",
	            new BookingDate(LocalDate.of(2025, 3, 19)), new BookingDate(LocalDate.of(2025, 3, 22))));
	        service.addBook(new Booking((Guest)service.searchUser("G010"), seoul, "106",
	            new BookingDate(LocalDate.of(2025, 3, 21)), new BookingDate(LocalDate.of(2025, 3, 24))));
	        service.addBook(new Booking((Guest)service.searchUser("G011"), seoul, "107",
	            new BookingDate(LocalDate.of(2025, 3, 23)), new BookingDate(LocalDate.of(2025, 3, 26))));
	        service.addBook(new Booking((Guest)service.searchUser("G012"), seoul, "101",
	            new BookingDate(LocalDate.of(2025, 3, 25)), new BookingDate(LocalDate.of(2025, 3, 28))));
	        service.addBook(new Booking((Guest)service.searchUser("G013"), seoul, "102",
	            new BookingDate(LocalDate.of(2025, 3, 27)), new BookingDate(LocalDate.of(2025, 3, 30))));
	        service.addBook(new Booking((Guest)service.searchUser("G014"), seoul, "103",
	            new BookingDate(LocalDate.of(2025, 3, 29)), new BookingDate(LocalDate.of(2025, 4, 1))));
	        service.addBook(new Booking((Guest)service.searchUser("G015"), seoul, "104",
	            new BookingDate(LocalDate.of(2025, 4, 1)),  new BookingDate(LocalDate.of(2025, 4, 5))));
	        service.addBook(new Booking((Guest)service.searchUser("G016"), seoul, "105",
	            new BookingDate(LocalDate.of(2025, 4, 3)),  new BookingDate(LocalDate.of(2025, 4, 7))));
	        service.addBook(new Booking((Guest)service.searchUser("G017"), seoul, "106",
	            new BookingDate(LocalDate.of(2025, 4, 5)),  new BookingDate(LocalDate.of(2025, 4, 9))));
	        service.addBook(new Booking((Guest)service.searchUser("G018"), seoul, "107",
	            new BookingDate(LocalDate.of(2025, 4, 7)),  new BookingDate(LocalDate.of(2025, 4, 10))));
	        service.addBook(new Booking((Guest)service.searchUser("G019"), seoul, "101",
	            new BookingDate(LocalDate.of(2025, 4, 9)),  new BookingDate(LocalDate.of(2025, 4, 13))));
	        service.addBook(new Booking((Guest)service.searchUser("G020"), seoul, "102",
	            new BookingDate(LocalDate.of(2025, 4, 11)), new BookingDate(LocalDate.of(2025, 4, 15))));
    
    
    }
		
}

class WeekSalesRanking {
	int week;
	double sales;
	
	public WeekSalesRanking(int week, double sales) {
		this.week = week;
		this.sales = sales;
	}
	@Override
    public String toString() {
        return week + 1 + "주차: " + sales + "입니다.";
    }
	
}