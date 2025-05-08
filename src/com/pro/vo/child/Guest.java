package com.pro.vo.child;

/**
 * <pre>
 * {@code
 * Guest 클래스는 User에서 상속받아 사용한다.
 * User의 자식 중에서 게스트 하우스를 이용하는 고객들이 해당된다.
 * }
 * </pre>
 * 
 * @author Kim SunHo
 * @version project version 0.0.1
 * @since JDK17
 */
import com.pro.vo.User;

public class Guest extends User{
	/**
	 * 국적 혹은 사는 지역
	 */
	private String nationality;
	/**
	 * 기본 생성자
	 */
	public Guest() {
		super();
	}
	/**
	 * 명시적 생성자
	 * @param id Guest의 ID
	 * @param name Guest의 이름
	 * @param phoneNumber Guest의 전화번호
	 * @param nationality Guest의 사는 지역
	 */
	public Guest(String id, String name, String phoneNumber, String nationality) {
		super(id, name, phoneNumber);
		this.nationality = nationality;
	}

	public String getNationality() {
		return nationality;
	}

	public void changeNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return super.toString() + " nationality=" + nationality;
	}
	
}
