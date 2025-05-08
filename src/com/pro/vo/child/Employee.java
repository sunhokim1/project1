package com.pro.vo.child;

import com.pro.vo.User;

public class Employee extends User{
	String dept;
	double salary;
	double bonus;
	public Employee() {
		super();
	}
	public Employee(String id, String name, String phoneNumber, String dept, double salary, double bonus) {
		super(id, name, phoneNumber);
		this.dept = dept;
		this.salary = salary;
		this.bonus = bonus;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void changeDept(String dept) {
		this.dept = dept;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void changeSalary(double salary) {
		this.salary = salary;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public void changeBonus(double bonus) {
		this.bonus = bonus;
	}
	@Override
	public String toString() {
		return super.toString() + "dept=" + dept + ", salary=" + salary + ", bonus=" + bonus;
	}
	
	
}
