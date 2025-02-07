package com.lm.ravi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lm.ravi.entity.IssueBook;

@Repository
public interface IssueBookRepository extends JpaRepository<IssueBook, Long>{
	@Query("SELECT COUNT(i) FROM IssueBook i")
	Integer getIssuedBooksCount();
	
	

}
