package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "langs")
public class Lang implements Serializable {
	private static final long serialVersionUID = -3236592576176671482L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lang_id")
	private int id;
	
	@Column(name = "lang_name")
	private String name;

	public Lang() {
		
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Lang temp = (Lang) obj;
		
		boolean nameEquals = false;
		if (name != null) {
			nameEquals = name.equals(temp.name);
		} else {
			nameEquals = (temp.name == null);
		}
		
		return nameEquals;
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		if (name != null) {
			res = res * PRIME + name.hashCode();
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Name: " + name;
	}
}
