package com.lm.ravi.repository;

import java.util.Optional;
import com.lm.ravi.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
	
	
	Optional<Admin> findByEmail(String email);
	
	
}
