package com.lm.ravi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String author;
	
	@Column(nullable=false)
	private String category;
	

	private int totalCopies;
	

	private int availableCopies;

	// Constructors
	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", totalCopies=" + totalCopies + ", availableCopies=" + availableCopies + "]";
	}

	
	// Getters & Setters
	
}