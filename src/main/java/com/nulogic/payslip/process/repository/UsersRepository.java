package com.nulogic.payslip.process.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nulogic.payslip.process.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	Users findByEmpid(String empid);

	Users findByEmailid(String email);

}
