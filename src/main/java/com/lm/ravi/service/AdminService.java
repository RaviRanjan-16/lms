package com.lm.ravi.service;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lm.ravi.entity.Admin;
import com.lm.ravi.repository.AdminRepository;

@Service
public class AdminService {
	
	private static final Logger log=LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private AdminRepository adminRepository;

	
	
	//Hard coded Admin setup (for first time-use)
	public void initializeAdmin() {
		if(adminRepository.findByEmail("admin@library.com").isEmpty()) {
			Admin admin = new Admin("admin@library.com","admin123");
			adminRepository.save(admin);
		}
	}
	public boolean authenticateAdmin(String email, String password) {
		
		Optional<Admin> adminOpt=adminRepository.findByEmail(email);
		return adminOpt.isPresent() && adminOpt.get().getPassword().equals(password);
	}
	
	public Optional<Admin> getAdminByEmail(String email){
		return adminRepository.findByEmail(email);
	}

}
