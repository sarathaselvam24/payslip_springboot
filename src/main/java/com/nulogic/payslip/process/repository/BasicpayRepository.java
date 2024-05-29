package com.nulogic.payslip.process.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Basicpay;
@Repository
public interface BasicpayRepository extends JpaRepository<Basicpay,String>{
	
	Basicpay findByEmpid(String employeeid);
	
	List<Basicpay> findAll();

}
