package com.nulogic.payslip.process.model;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Salarydetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private BigDecimal payabledays;
	
	private BigDecimal paidmonth;
	
	private BigDecimal deduction;
	
	private BigDecimal total;

	public Salarydetails() {
		super();
	}

	public Salarydetails(String employeeid, BigDecimal basicpay, BigDecimal houseallowance, BigDecimal specialallowance,
			BigDecimal otallowance, BigDecimal providentfund, BigDecimal professionaltax, BigDecimal salaryadvance,
			BigDecimal netpay,BigDecimal payabledays,BigDecimal paidmonth, String month, String year,BigDecimal overtime) {
		super();
		BigDecimal oneDayBasicPay = basicpay.divide(payabledays, 0, RoundingMode.HALF_UP);
		BigDecimal overtimePay = BigDecimal.ZERO;
		if (overtime.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal fullDays = overtime.divide(new BigDecimal("8"), 0, RoundingMode.DOWN);
			BigDecimal remainingHours = overtime.remainder(new BigDecimal("8"));
			overtimePay = overtimePay.add(oneDayBasicPay.multiply(fullDays));
			if (remainingHours.compareTo(new BigDecimal("4")) >= 0) {
				overtimePay = overtimePay.add(oneDayBasicPay.divide(new BigDecimal("2"), 0, RoundingMode.HALF_UP));
			}
		}
		System.out.println("payabledays "+payabledays+" paidmonth "+paidmonth);
		System.out.println("overtimePay"+overtimePay);
		this.empid=employeeid;
		this.basicpay = basicpay;
		this.houseallowance = houseallowance;
		this.specialallowance = specialallowance;
		this.otallowance = otallowance.add(overtimePay);
		this.providentfund = providentfund;
		this.professionaltax = professionaltax;
		this.salaryadvance = salaryadvance;
		this.payslipmonth = month;
		this.payslipyear = year;
		 System.out.println("this.netpay 1 "+this.netpay);
		 netpay = basicpay.add((houseallowance).add((specialallowance)
				.add((this.otallowance))));
        this.payabledays=payabledays;
        this.paidmonth=paidmonth;
        this.total = netpay;
        System.out.println("this.paidmonth "+this.paidmonth);
        BigDecimal calculateLossOfDay  = this.payabledays.subtract(this.paidmonth);
        System.out.println("calculateLossOfDay "+calculateLossOfDay);
        BigDecimal dayPay = netpay.divide(this.payabledays, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
        System.out.println("dayPay "+dayPay);
        netpay = netpay.subtract(dayPay.multiply(calculateLossOfDay));
		BigDecimal	deduction = providentfund.add(professionaltax).add(salaryadvance);
		this.netpay = netpay.subtract(deduction);
		 System.out.println("this.netpay "+this.netpay);
		 this.deduction = deduction;
     
	}

	private String empid;

	private BigDecimal basicpay;

	private BigDecimal houseallowance;

	private BigDecimal specialallowance;

	private BigDecimal otallowance;

	private BigDecimal providentfund;

	private BigDecimal professionaltax;

	private BigDecimal salaryadvance;

	private BigDecimal netpay;

	private String payslipmonth;

	private String payslipyear;

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

	public BigDecimal getBasicpay() {
		return basicpay;
	}

	public void setBasicpay(BigDecimal basicpay) {
		this.basicpay = basicpay;
	}

	public BigDecimal getHouseallowance() {
		return houseallowance;
	}

	public void setHouseallowance(BigDecimal houseallowance) {
		this.houseallowance = houseallowance;
	}

	public BigDecimal getSpecialallowance() {
		return specialallowance;
	}

	public void setSpecialallowance(BigDecimal specialallowance) {
		this.specialallowance = specialallowance;
	}

	public BigDecimal getOtallowance() {
		return otallowance;
	}

	public void setOtallowance(BigDecimal otallowance) {
		this.otallowance = otallowance;
	}

	public BigDecimal getProvidentfund() {
		return providentfund;
	}

	public void setProvidentfund(BigDecimal providentfund) {
		this.providentfund = providentfund;
	}

	public BigDecimal getProfessionaltax() {
		return professionaltax;
	}

	public void setProfessionaltax(BigDecimal professionaltax) {
		this.professionaltax = professionaltax;
	}

	public BigDecimal getSalaryadvance() {
		return salaryadvance;
	}

	public void setSalaryadvance(BigDecimal salaryadvance) {
		this.salaryadvance = salaryadvance;
	}

	public String getPayslipmonth() {
		return payslipmonth;
	}

	public void setPayslipmonth(String payslipmonth) {
		this.payslipmonth = payslipmonth;
	}

	public String getPayslipyear() {
		return payslipyear;
	}

	public void setPayslipyear(String payslipyear) {
		this.payslipyear = payslipyear;
	}

	public BigDecimal getNetpay() {
		return netpay;
	}

	public void setNetpay(BigDecimal netpay) {
		this.netpay = netpay;
	}

	public BigDecimal getPayabledays() {
		return payabledays;
	}

	public void setPayabledays(BigDecimal payabledays) {
		this.payabledays = payabledays;
	}

	public BigDecimal getPaidmonth() {
		return paidmonth;
	}

	public void setPaidmonth(BigDecimal paidmonth) {
		this.paidmonth = paidmonth;
	}

	public BigDecimal getDeduction() {
		return deduction;
	}

	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


}
