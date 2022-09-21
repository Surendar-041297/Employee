package com.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "employee_name")
	private String name;
	@Column(name = "employee_designation")
	private String designation;
	@Column(name = "salary")
	private Long salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public Long getId() {
		return id;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public Employee(String name, String designation, Long salary) {
		super();
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}
	public Employee() {
		
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary + "]";
	}
}
