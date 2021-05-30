package com.example.ticketsystem.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "TICKET")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ticketId", scope = Ticket.class)
public class Ticket implements Serializable {

	public static final long serialVersionUID = 2L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@Column
	@NotNull
	private String title;

	@Column
	@NotNull
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "assignedTo", referencedColumnName = "userId")
	private User assignedTo;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ownerId", referencedColumnName = "userId")
	private User owner;

	@Column
	@NotNull
	private Boolean priority;

	@Column
	@NotNull
	private Integer status;
	
	@Column
	@NotNull
	private Boolean ticketType;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date statusUpdatedOn;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date updatedOn;

	@Column
	@NotNull
	private Long updatedBy;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Boolean getPriority() {
		return priority;
	}

	public void setPriority(Boolean priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStatusUpdatedOn() {
		return statusUpdatedOn;
	}

	public void setStatusUpdatedOn(Date statusUpdatedOn) {
		this.statusUpdatedOn = statusUpdatedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public Boolean getTicketType() {
		return ticketType;
	}

	public void setTicketType(Boolean ticketType) {
		this.ticketType = ticketType;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
}
