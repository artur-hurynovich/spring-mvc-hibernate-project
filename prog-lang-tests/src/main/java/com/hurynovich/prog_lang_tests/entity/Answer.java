package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answer implements Serializable {
	private static final long serialVersionUID = -5438031953572633323L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private int id;
	
	@Column(name = "answer_text")
	private String text;
	
	@Column(name = "answer_correct")
	private Boolean correct;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
	private Question questions;
	
	public Answer() {
		
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
	
	public Boolean getCorrect() {
		return correct;
	}
	
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	public Question getQuestios() {
		return questions;
	}

	public void setQuestion(Question questions) {
		this.questions = questions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Answer temp = (Answer) obj;
		
		boolean textEquals = false;
		if (text != null) {
			textEquals = text.equals(temp.text);
		} else {
			textEquals = (temp.text == null);
		}
		
		boolean correctEquals = false;
		if (correct != null) {
			correctEquals = correct.equals(temp.correct);
		} else {
			correctEquals = (temp.correct == null);
		}
		
		return (textEquals && correctEquals);
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		if (text != null) {
			res = res * PRIME + text.hashCode();
		}
		
		if (correct != null) {
			res = res * PRIME + correct.hashCode(); 
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Text: " + text + "\n"
		+ "Correct: " + correct;
	}
}
