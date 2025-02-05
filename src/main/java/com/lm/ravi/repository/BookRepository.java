package com.lm.ravi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lm.ravi.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	
	@Query("SELECT SUM(b.totalCopies) FROM Book b")
	Integer getTotalBooksCount();
	
	@Query("SELECT SUM(b.availableCopies) FROM Book b WHERE b.availableCopies > 0")
	Integer getTotalCopiesAvailable();
	

}
