package com.nulogic.payslip.process.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nulogic.payslip.process.model.Employee;
import com.nulogic.payslip.process.model.EmployeeAccountDetails;
import com.nulogic.payslip.process.repository.EmployeeAccountDetailsRepository;
import com.nulogic.payslip.process.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAccountDetailsRepository accountDetailsRepository;

    // POST method to get employee details
    @PostMapping("/api/employee")
    public ResponseEntity<Employee> getEmployee(@RequestBody Map<String, String> request) {
        String empid = request.get("empid");
        System.out.println("empid "+empid);
        Optional<Employee> employee = employeeRepository.findByEmpid(empid);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST method to get employee account details
    @PostMapping("/api/employeeAccountDetails")
    public ResponseEntity<EmployeeAccountDetails> getEmployeeAccountDetails(@RequestBody Map<String, String> request) {
    	String empid = request.get("empid");
    	 
    	 Optional<EmployeeAccountDetails> employeeAccountData = accountDetailsRepository.findByEmpid(empid);
    	 return employeeAccountData.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/api/saveEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    @PostMapping("/api/saveEmployeeAccountDetails")
    public ResponseEntity<EmployeeAccountDetails> saveEmployeeAccountDetails(@RequestBody EmployeeAccountDetails accountDetails) {
        EmployeeAccountDetails savedAccountDetails = accountDetailsRepository.save(accountDetails);
        return ResponseEntity.ok(savedAccountDetails);
    }

}
