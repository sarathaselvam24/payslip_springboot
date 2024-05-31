package com.nulogic.payslip.process.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Overtime;

@Repository
public interface OvertimeRepo extends JpaRepository<Overtime,Integer>{
	
	@Query(value = "SELECT * FROM overtime WHERE month = ?1 AND year =?2 AND empid =?3", nativeQuery = true)
	Overtime exitsOverTime(String month,String year,String employeeid);

}
