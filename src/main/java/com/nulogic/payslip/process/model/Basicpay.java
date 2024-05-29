package com.nulogic.payslip.process.model;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Basicpay {
	@Id
	private String empid;
	
	private String emailid;
	
	private BigDecimal ctc;
	
	private String shift;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	
	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public BigDecimal getCtc() {
		return ctc;
	}

	public void setCtc(BigDecimal ctc) {
		this.ctc = ctc;
	}

	@Override
	public String toString() {
		return "Basicpay [empid=" + empid + ", emailid=" + emailid + ", ctc=" + ctc + ", shift=" + shift + "]";
	}
}
