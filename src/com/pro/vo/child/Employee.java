package com.pro.vo.child;

/**
 * <pre>
 * {@code
 * Employee 클래스는 User에서 상속받아 사용한다.
 * User의 자식 중에서 게스트하우스 본사에서 근무하는 직원들이 해당된다.
 * }
 * </pre>
 * 
 * @author Kim SunHo
 * @version project version 0.0.1
 * @since JDK17
 */
import com.pro.vo.User;

public class Employee extends User{
	/**
	 * Employee의 부서 정보
	 */
	private String dept;
	/**
	 * Employee의 급여 정보
	 */
	private double salary;
	/**
	 * Employee의 보너스 정보
	 */
	private double bonus;
	/*
	 * Employee의 기본 생성자
	 */
	public Employee() {
		super();
	}
	/**
	 * Employee의 명시적 생성자
	 * @param id Employee의 ID
	 * @param name Employee의 이름
	 * @param phoneNumber Employee의 전화번호
	 * @param dept Employee의 부서
	 * @param salary Employee의 급여
	 * @param bonus Employee의 보너스
	 */
	public Employee(String id, String name, String phoneNumber, String dept, double salary, double bonus) {
		super(id, name, phoneNumber);
		this.dept = dept;
		this.salary = salary;
		this.bonus = bonus;
	}
	/**
	 * Employee가 속한 부서를 반환한다.
	 * @return
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * Employee의 소속 부서를 변경한다.
	 * @param dept
	 */
	public void changeDept(String dept) {
		this.dept = dept;
	}
	/**
	 * Employee의 급여를 반환한다.
	 * @return
	 */
	public double getSalary() {
		return salary;
	}
	/**
	 * Employee의 급여를 변경한다.
	 * @param salary
	 */
	public void changeSalary(double salary) {
		this.salary = salary;
	}
	/**
	 * Employee의 보너스를 반환한다.
	 * @return
	 */
	public double getBonus() {
		return bonus;
	}
	/**
	 * Employee의 보너스를 변경한다.
	 * @param bonus
	 */
	public void changeBonus(double bonus) {
		this.bonus = bonus;
	}
	/**
	 * User의 정보와 Employee의 정보를 반환한다.
	 */
	@Override
	public String toString() {
		return super.toString() + "dept=" + dept + ", salary=" + salary + ", bonus=" + bonus;
	}
	
	
}
