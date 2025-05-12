package com.pro.service;

import com.pro.exception.InvalidTransactionException;

/**
 * RevenueService 인터페이스는 연도, 월, 분기, 연도+숙소 기준의 매출 통계 조회 기능을 제공한다.
 * 매출 합계 및 성수기 분기 계산 메서드를 정의한다.
 *
 * @author LEE TAE RAN
 * @version 1.0.0
 * @since JDK17
 */
public interface RevenueService {
	/**
     * 지정된 연도와 월의 전체 매출을 조회한다.
     *
     * @param month 조회할 달 
     * @param day 조회할 일 (1~30)
     * @return 지정된 기간의 매출 합계
     */
	double getSalesForDay(int month, int day)throws InvalidTransactionException;
	/**
     * 지정된 월의 주별 매출을 조회한다.
     *
     * @param month    조회할 
     * @param weekly 조회할 주(1~4)
     * @return 지정된 기간의 매출 합계
     */
	double getSalesForWeekly(int month, int weekly)throws InvalidTransactionException;
	/**
     * 지정된 달의 전체 매출을 조회한다.
     *
     * @param month 조회할 달 
     * @return 지정된 연도의 매출 합계
     */
	double getSalesForMonth(int month)throws InvalidTransactionException;
	/**
     * 지정된 달과 일, 게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param month            조회할 달
     * @param day              조회할 일 (1~30)
     * @param guestHouseName  조회할 게스트하우스 이름
     * @return 지정된 기간 및 숙소의 매출 합계
     */
	double getSalesForDay(int month, int day, String guestHouseName)throws InvalidTransactionException;
	/**
     * 지정된 달과 주별, 게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param month            조회할 달 
     * @param weekly           조회할 주 (1~4)
     * @param guestHouseName  조회할 게스트하우스 이름
     * @return 지정된 기간 및 숙소의 매출 합계
     */
	double getSalesForQuarter(int month, int weekly, String guestHouseName)throws InvalidTransactionException;
	/**
     * 지정된 달과  게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param month            조회할 달 
     * @param guestHouseName   조회할 게스트하우스 이름
     * @return 지정된 달의 매출 합계
     */
	double getSalesForMonth(int month, String guestHouseName)throws InvalidTransactionException;
	 /**
     * 지정된 달의 성수기 주를 반환한다.
     *
     * @param month 조회할 달
     * @return 가장 높은 매출을 기록한 주 (1~4)
     */
	int getPeakSeason(int month)throws InvalidTransactionException;
}
