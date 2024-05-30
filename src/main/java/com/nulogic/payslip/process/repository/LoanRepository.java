package com.nulogic.payslip.process.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
	
	@Query(value = "SELECT * FROM loan WHERE empid = ?1 AND loanstatus =?2 AND loanrequeststatus =?3 AND remainingbalance >?4", nativeQuery = true)
	Loan findLoanrequest(String empid,String loanstatus,String loanrequeststatus,BigDecimal remainingbalance);
	
	@Query(value = "SELECT * FROM loan WHERE empid = ?1 AND loanstatus =?2 AND loanrequeststatus =?3", nativeQuery = true)
	Loan findLoanNotStaredrequest(String empid,String loanstatus,String loanrequeststatus);
	
	List<Loan> findByEmpidContainingOrLoanstatusContaining(String empid, String loanstatus);
	
	@Query(value = "SELECT * FROM loan WHERE empid = ?1 AND (loanrequeststatus = ?2 OR loanrequeststatus = ?3)", nativeQuery = true)
	List<Loan> findByEmpidAndLoanRequestStatus(String empid, String loanRequestOngoing,String loanrequestNotStarted);
	
	Optional<List<Loan>> findByEmpid(String employeeid);

	Optional<List<Loan>> findByLoanstatusContaining(String status);

}
