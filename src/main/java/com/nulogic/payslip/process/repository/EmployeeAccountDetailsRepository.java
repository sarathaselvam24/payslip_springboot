package com.nulogic.payslip.process.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.EmployeeAccountDetails;

@Repository
public interface EmployeeAccountDetailsRepository extends JpaRepository<EmployeeAccountDetails, String> {

	EmployeeAccountDetails findByEmpid(String empid);

}
