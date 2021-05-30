package com.example.ticketsystem.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "USER")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "userId", scope = User.class)
public class User implements Serializable {

	private static final long serialVersionUID = 2L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private String mobileNumber;
	@Column
	@NotNull
	private String keyString;
	
	@Column
	@NotNull
	private Integer role;
	 
	@Column
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}
	
	
	
}
