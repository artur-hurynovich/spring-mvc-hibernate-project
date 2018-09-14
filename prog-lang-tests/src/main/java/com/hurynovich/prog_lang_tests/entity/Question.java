package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
	private static final long serialVersionUID = -8171743594947818028L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int id;
	
	@Column(name = "question_text")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
	private Test tests;
	
	@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
	private List<Answer> answers = new LinkedList<>();
	
	public Question() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Test getTest() {
		return tests;
	}
	
	public void setTest(Test test) {
		this.tests = test;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Question temp = (Question) obj;
		
		boolean textEquals = false;
		if (text != null) {
			textEquals = text.equals(temp.text);
		} else {
			textEquals = (temp.text == null);
		}
		
		return textEquals;
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		if (text != null) {
			res = res * PRIME + text.hashCode();
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Text: " + text;
	}
}
