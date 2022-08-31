package com.spyglass.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	public Account() {}

	public Account(String email, String password, String firstName, String lastName, String dob) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	
	public Account(int id, String email, String password, String firstName, String lastName, String dob) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private int id;
	
	private String email;
	
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String dob;
	
//	@OneToMany(mappedBy="account")
//	private List<Goal> goals;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
//	public List<Goal> getGoals() {
//		return goals;
//	}
//
//	public void setGoals(List<Goal> goals) {
//		this.goals = goals;
//	}
//
//	@Override
//	public String toString() {
//		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
//				+ ", lastName=" + lastName + ", dob=" + dob + ", goals=" + goals + "]";
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password);
	}

}
