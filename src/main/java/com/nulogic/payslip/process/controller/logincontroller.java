package com.nulogic.payslip.process.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nulogic.payslip.process.model.Users;
import com.nulogic.payslip.process.repository.UsersRepository;

@RestController
public class logincontroller {
	@Autowired
	private UsersRepository userRepo;

	@PostMapping("getEmployeeRoll")
	public ResponseEntity<Users> postData(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		Users user = userRepo.findByEmailid(email);

		if (null != user) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
}
