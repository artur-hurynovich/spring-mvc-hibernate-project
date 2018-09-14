package com.hurynovich.prog_lang_tests.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 2248358166938245052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_email")
	private String email;

	@Column(name = "user_password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", columnDefinition = "ENUM('USER','ADMIN')")
	private UserRole role;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		User temp = (User) obj;
		
		boolean nameEquals = false;
		if (name != null) {
			nameEquals = name.equals(temp.name);
		} else {
			nameEquals = (temp.name == null);
		}
		
		boolean emailEquals = false;
		if (email != null) {
			emailEquals = email.equals(temp.email);
		} else {
			emailEquals = (temp.email == null);
		}
		
		boolean passwordEquals = false;
		if (password != null) {
			passwordEquals = password.equals(temp.password);
		} else {
			passwordEquals = (temp.password == null);
		}
		
		boolean roleEquals = false;
		if (role != null) {
			roleEquals = role.equals(temp.role);
		} else {
			roleEquals = (temp.role == null);
		}
		
		return (nameEquals && emailEquals && passwordEquals && roleEquals);
	}
	
	@Override
	public int hashCode() {
		int res = 13;
		final int PRIME = 25;
		
		if (name != null) {
			res = res * PRIME + name.hashCode();
		}
		
		if (email != null) {
			res = res * PRIME + email.hashCode();
		}
		
		if (password != null) {
			res = res * PRIME + password.hashCode();
		}
		
		if (role != null) {
			res = res * PRIME + role.hashCode();
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\n"
		+ "Name: " + name + "\n"
		+ "E-mail: " + email + "\n"
		+ "Role: " + role + "\n"
		+ "Password: " + password;
	}
}
