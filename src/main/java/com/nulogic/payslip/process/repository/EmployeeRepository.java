package com.nulogic.payslip.process.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Employee findByEmpid(String employeeid);
	
	List<Employee> findAll();


}
