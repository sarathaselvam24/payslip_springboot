package com.nulogic.payslip.process.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Salarydetails;

@Repository
public interface SalarydetailsRepository extends JpaRepository<Salarydetails, Integer> {
	
	@Query(value = "SELECT * FROM salarydetails WHERE payslipmonth = ?1 AND payslipyear =?2 AND empid =?3", nativeQuery = true)
	Salarydetails findByPayslip(String month,String year,String employeeid);

}
