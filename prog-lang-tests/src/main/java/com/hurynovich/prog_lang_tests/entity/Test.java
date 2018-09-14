package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tests")
@NamedEntityGraph(
	name = "completeTestGraph",
	attributeNodes = {
		@NamedAttributeNode(value = "questions", subgraph = "questionsGraph")
	},
	subgraphs = {
		@NamedSubgraph(
			name = "questionsGraph",
			attributeNodes = {
				@NamedAttributeNode(value = "answers")
			}
		)
	}
)
public class Test implements Serializable {
	private static final long serialVersionUID = 5156263234924122332L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id")
	private int id;
	
	@Column(name = "test_name")
	@NotEmpty(message = "Please, enter test name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "test_difficulty", columnDefinition = "ENUM('EASY','MEDIUM','HARD')")
	private TestDifficulty difficulty;
	
	@OneToMany(mappedBy = "tests", cascade = CascadeType.ALL)
	private Set<Question> questions = new HashSet<>();
	
	@Column(name = "lang_id")
	private Integer langId;
	
	public Test() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TestDifficulty getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(TestDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Integer getLangId() {
		return langId;
	}
	
	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Test temp = (Test) obj;
		
		boolean nameEquals = false;
		if (name != null) {
			nameEquals = name.equals(temp.name);
		} else {
			nameEquals = (temp.name == null);
		}
		
		boolean difficultyEquals = false;
		if (difficulty != null) {
			difficultyEquals = difficulty.equals(temp.difficulty);
		} else {
			difficultyEquals = (temp.difficulty == null);
		}
		
		return (nameEquals && difficultyEquals);
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		if (name != null) {
			res = res * PRIME + name.hashCode();
		}
		
		if (difficulty != null) {
			res = res * PRIME + difficulty.hashCode();
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Name: " + name + "\n"
		+ "Difficulty: " + difficulty;
	}
}
