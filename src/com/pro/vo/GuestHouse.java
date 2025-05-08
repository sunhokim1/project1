package com.pro.vo;

import java.util.HashMap;
import java.util.Map;
/**
 * VO class representing a GuestHouse with basic information.
 */
public class GuestHouse {
	/**
     * 게스트하우스 이름 (고유 식별자 역할)
     */
	private String name;
	 /**
     * 게스트하우스 주소
     */
	private String address;
	 /**
     * 방 목록: 방 번호(String) → 수용 인원(Integer)
     */
	private HashMap<String, Integer> rooms;
    /**
     * 주변 명소 정보
     */
	private String hotPlace;
	 /**
     * 1박 기준 기본 요금
     */
	private double price;
	 /**
     * Constructs a new GuestHouse VO.
     *
     * @param name       게스트하우스 이름 (고유 식별자)
     * @param rooms      방 목록(방 번호 → 수용 인원)
     * @param address    게스트하우스 주소
     * @param hotPlace   주변 명소 정보
     * @param price      1박 기준 기본 요금
     */
	public GuestHouse(String name, String address, HashMap<String, Integer> rooms, String hotPlace, double price) {
		super();
		this.name = name;
		this.address = address;
		this.rooms = rooms;
		this.hotPlace = hotPlace;
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the rooms
	 */
	public HashMap<String, Integer> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(HashMap<String, Integer> rooms) {
		this.rooms = rooms;
	}

	/**
	 * @return the hotPlace
	 */
	public String getHotPlace() {
		return hotPlace;
	}

	/**
	 * @param hotPlace the hotPlace to set
	 */
	public void setHotPlace(String hotPlace) {
		this.hotPlace = hotPlace;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	 /**
     * @return string representation
     */
	@Override
	public String toString() {
		return "GuestHouse [name=" + name + ", address=" + address + ", rooms=" + rooms + ", hotPlace=" + hotPlace
				+ ", price=" + price + "]";
	}
	
	
	
	

}
