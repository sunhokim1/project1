package com.pro.vo;

/**
 * <pre>
 * {@code
 * User 클래스는 서비스를 이용하는 모든 이용자들의
 * ID와 이름, 전화번호를 가진 abstract class이다.
 * child 들에게 공통된 필드와 메소드를 상속만 하는 클래스이다.
 * }
 * </pre>
 * @author Kim SunHo
 * @version project version 0.0.1
 * @since JDK17
 */

public abstract class User {
	/**
	 * 사용자의 ID 정보
	 */
	private String id;
	/**
	 * 사용자의 이름
	 */
	private String name;
	/**
	 * 사용자의 전화번호
	 */
	private String phoneNumber;
	/**
	 * User클래스의 기본 생성자
	 */
	public User() {}
	/**
	 * 명시적 생성자 User
	 * @param id
	 * @param name
	 * @param phoneNumber
	 */
	public User(String id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 사용자의 정보를 반환한다.
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", phone number=" + phoneNumber;
	}
	/**
	 * 사용자의 ID를 반환한다.
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 사용자의 이름을 반환한다.
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 사용자의 전화번호를 반환한다.
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * 사용자의 전화번호를 변경한다.
	 * @param phoneNumber
	 */
	public void changePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
