package com.lm.ravi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "issued_books")
public class IssueBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "issue_date")
    private LocalDate issueDate;
    
    private boolean issuedStatus = false;

    public IssueBook() {}

    public IssueBook(User user, Book book, LocalDate issueDate) {
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
    }
    
    
    

    

	@Override
	public String toString() {
		return "IssueBook [id=" + id + ", user=" + user + ", book=" + book + ", issueDate=" + issueDate
				+ ", issuedStatus=" + issuedStatus + "]";
	}

	public boolean isIssuedStatus() {
		return issuedStatus;
	}

	public void setIssuedStatus(boolean issuedStatus) {
		this.issuedStatus = issuedStatus;
	}

	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
}