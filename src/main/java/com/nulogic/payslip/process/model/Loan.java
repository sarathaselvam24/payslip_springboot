package com.nulogic.payslip.process.model;


import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String empid;
	
	private String emailid;
	
	private BigDecimal basicpay;
	
	private BigDecimal loanamount;
	
	private Date expectedmonth;
	
	private Date emistartsfrom;
	
	private BigDecimal repaymentterms;
	
	private String requestedby;
	
	private String loanrequeststatus;
	
	private Date issuedon;
	
	private String note;
	
	private BigDecimal remainingbalance;
	
	private String loanstatus;
	
	private String approvedby;
	
	private String rejectedby;
	
	private String canceledby;
	
	private BigDecimal emi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public BigDecimal getBasicpay() {
		return basicpay;
	}

	public void setBasicpay(BigDecimal basicpay) {
		this.basicpay = basicpay;
	}

	public BigDecimal getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(BigDecimal loanamount) {
		this.loanamount = loanamount;
	}

	public Date getExpectedmonth() {
		return expectedmonth;
	}

	public void setExpectedmonth(Date expectedmonth) {
		this.expectedmonth = expectedmonth;
	}

	public Date getEmistartsfrom() {
		return emistartsfrom;
	}

	public void setEmistartsfrom(Date emistartsfrom) {
		this.emistartsfrom = emistartsfrom;
	}

	public BigDecimal getRepaymentterms() {
		return repaymentterms;
	}

	public void setRepaymentterms(BigDecimal paymentterms) {
		this.repaymentterms = paymentterms;
	}

	public String getRequestedby() {
		return requestedby;
	}

	public void setRequestedby(String requestedby) {
		this.requestedby = requestedby;
	}

	public String getLoanrequeststatus() {
		return loanrequeststatus;
	}

	public void setLoanrequeststatus(String loanrequeststatus) {
		this.loanrequeststatus = loanrequeststatus;
	}

	public Date getIssuedon() {
		return issuedon;
	}

	public void setIssuedon(Date issuedon) {
		this.issuedon = issuedon;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getRemainingbalance() {
		return remainingbalance;
	}

	public void setRemainingbalance(BigDecimal remainingbalance) {
		this.remainingbalance = remainingbalance;
	}

	public String getLoanstatus() {
		return loanstatus;
	}

	public void setLoanstatus(String loanstatus) {
		this.loanstatus = loanstatus;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getRejectedby() {
		return rejectedby;
	}

	public void setRejectedby(String rejectedby) {
		this.rejectedby = rejectedby;
	}

	public String getCanceledby() {
		return canceledby;
	}

	public void setCanceledby(String canceledby) {
		this.canceledby = canceledby;
	}

	public BigDecimal getEmi() {
		return emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", empid=" + empid + ", emailid=" + emailid + ", basicpay=" + basicpay
				+ ", loanamount=" + loanamount + ", expectedmonth=" + expectedmonth + ", emistartsfrom=" + emistartsfrom
				+ ", repaymentterms=" + repaymentterms + ", requestedby=" + requestedby + ", loanrequeststatus="
				+ loanrequeststatus + ", issuedon=" + issuedon + ", note=" + note + ", remainingbalance="
				+ remainingbalance + ", loanstatus=" + loanstatus + ", approvedby=" + approvedby + ", rejectedby="
				+ rejectedby + ", canceledby=" + canceledby + ", emi=" + emi + "]";
	}
}
