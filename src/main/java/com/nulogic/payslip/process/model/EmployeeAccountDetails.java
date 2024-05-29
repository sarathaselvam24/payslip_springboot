package com.nulogic.payslip.process.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeAccountDetails {

	public EmployeeAccountDetails() {
		super();
	}

	public EmployeeAccountDetails(String empid) {
		super();
		this.empid = empid;
	}

	@Id
	private String empid;

	private String empname;

	private String accountnumber;

	private String ifsccode;

	private String bankname;

	private String pannumber;

	private String uannumber;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getUannumber() {
		return uannumber;
	}

	public void setUannumber(String uannumber) {
		this.uannumber = uannumber;
	}

	@Override
	public String toString() {
		return "EmployeeAccountDetails [empid=" + empid + ", empname=" + empname + ", accountnumber=" + accountnumber
				+ ", ifsccode=" + ifsccode + ", bankname=" + bankname + ", pannumber=" + pannumber + ", uannumber="
				+ uannumber + "]";
	}

}
