package com.nulogic.payslip.process.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Optional<Employee> findByEmpid(String empid);
	
	List<Employee> findAll();


}
