package com.nulogic.payslip.process.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "overtime")
public class Overtime {
	@Id
	private String empid;
	
	private BigDecimal overtime;
	
	private String month;
	
	private String year;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public BigDecimal getOvertime() {
		return overtime;
	}

	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Overtime [empid=" + empid  + ", overtime=" + overtime + ", month=" + month
				+ ", year=" + year + "]";
	}
	

}
