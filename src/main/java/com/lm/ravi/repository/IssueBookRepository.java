package com.lm.ravi.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lm.ravi.entity.IssueBook;

public interface IssueBookRepository extends JpaRepository<IssueBook, Long>{
	

}
