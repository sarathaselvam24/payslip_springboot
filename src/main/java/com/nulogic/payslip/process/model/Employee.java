package com.nulogic.payslip.process.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	public Employee() {
		super();
	}

	public Employee(String empid) {
		super();
		this.empid = empid;
	}

	@Id
	private String empid;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "address")
	private String address;

	@Column(name = "joiningdate")
	private Date joiningdate;

	@Column(name = "designation")
	private String designation;

	@Column(name = "location")
	private String location;

	@Column(name = "companyname")
	private String companyname;

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "dateofbirth")
	private Date dateofbirth;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", email=" + email + ", name=" + name + ", phonenumber=" + phonenumber
				+ ", address=" + address + ", joiningdate=" + joiningdate + ", designation=" + designation
				+ ", location=" + location + ", companyname=" + companyname + ", dateofbirth=" + dateofbirth + "]";
	}

}