package com.pro.service;
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
     * @param year  조회할 연도
     * @param month 조회할 월 (1~12)
     * @return 지정된 기간의 매출 합계
     */
	double getSalesForMonth(int year, int month);
	/**
     * 지정된 연도와 분기의 전체 매출을 조회한다.
     *
     * @param year    조회할 연도
     * @param quarter 조회할 분기 (1~4)
     * @return 지정된 기간의 매출 합계
     */
	double getSalesForQuarter(int year, int quarter);
	/**
     * 지정된 연도의 전체 매출을 조회한다.
     *
     * @param year 조회할 연도
     * @return 지정된 연도의 매출 합계
     */
	double getSalesForYear(int year);
	/**
     * 지정된 연도와 월, 게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param year            조회할 연도
     * @param month           조회할 월 (1~12)
     * @param guestHouseName  조회할 게스트하우스 이름
     * @return 지정된 기간 및 숙소의 매출 합계
     */
	double getSalesForMonth(int year, int month, String guestHouseName);
	/**
     * 지정된 연도와 분기, 게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param year            조회할 연도
     * @param quarter         조회할 분기 (1~4)
     * @param guestHouseName  조회할 게스트하우스 이름
     * @return 지정된 기간 및 숙소의 매출 합계
     */
	double getSalesForQuarter(int year, int quarter, String guestHouseName);
	/**
     * 지정된 연도와 게스트하우스 이름을 기준으로 매출을 조회한다.
     *
     * @param year            조회할 연도
     * @param guestHouseName  조회할 게스트하우스 이름
     * @return 지정된 연도의 매출 합계
     */
	double getSalesForYear(int year, String guestHouseName);
	 /**
     * 지정된 연도의 성수기 분기를 반환한다.
     *
     * @param year 조회할 연도
     * @return 가장 높은 매출을 기록한 분기 (1~4)
     */
	int getPeakSeason(int year);
}
