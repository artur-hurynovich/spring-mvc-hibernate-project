package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Statistics implements Serializable {
	private static final long serialVersionUID = 503245842697303253L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stats_id")
	private int id;
	
	@Column(name = "test_id")
	private int testId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "res")
	private int result;

	public Statistics() {
		
	}
	
	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Statistics temp = (Statistics) obj;
		
		boolean testIdEquals = (testId == temp.testId);

		boolean userIdEquals = (userId == temp.userId);
		
		boolean resultEquals = (result == temp.result);
		
		return (testIdEquals && userIdEquals && resultEquals);
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		res = res * PRIME + testId;
		
		res = res * PRIME + userId;
		
		res = res * PRIME + result;
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Test ID: " + testId + "\n"
		+ "User ID: " + userId + "\n"
		+ "Result: " + result;
	}
}
