package com.pro.service;

public interface RevenueService {
	double getSalesForMonth(int year, int month);
	double getSalesForQuarter(int year, int quarter);
	double getSalesForYear(int year);
	double getSalesForMonth(int year, int month, String guestHouseName);
	double getSalesForQuarter(int year, int quarter, String guestHouseName);
	double getSalesForYear(int year, String guestHouseName);
	int getPeakSeason(int year);
}
