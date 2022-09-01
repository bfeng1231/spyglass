package com.spyglass.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name="goal")
public class Goal {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="goal_id")
	private int id;
	
	private String name;
	
	private String description;
	
	private String picture;
	
	@Column(name="target_date")
	private String targetDate;
	
	@Column(name="target_amount")
	private int targetAmount;
	
	@Column(name="current_amount")
	private int currentAmount;
	
//	@Column(name="account_id")
//	private int accountId;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Account account;

	public Goal() {}
	
	public Goal(int id, String name, String description, String picture, String targetDate, int targetAmount,
			int currentAmount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public int getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(int targetAmount) {
		this.targetAmount = targetAmount;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}

//	public int getAccountId() {
//		return accountId;
//	}
//
//	public void setAccountId(int accountId) {
//		this.accountId = accountId;
//	}
//	
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

//	@Override
//	public String toString() {
//		return "Goal [id=" + id + ", name=" + name + ", description=" + description + ", picture=" + picture
//				+ ", targetDate=" + targetDate + ", targetAmount=" + targetAmount + ", currentAmount=" + currentAmount
//				+ ", account=" + account + "]";
//	}

}
