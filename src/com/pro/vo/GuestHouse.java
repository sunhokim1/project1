package com.pro.vo;

import java.util.HashMap;
import java.util.Map;

public class GuestHouse {
	private String name;
	private String address;
	private HashMap<String, Integer> rooms;
	private String hotPlace;
	private double price;
	
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

	@Override
	public String toString() {
		return "GuestHouse [name=" + name + ", address=" + address + ", rooms=" + rooms + ", hotPlace=" + hotPlace
				+ ", price=" + price + "]";
	}
	
	
	
	

}
