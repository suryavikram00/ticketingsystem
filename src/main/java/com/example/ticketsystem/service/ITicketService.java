package com.example.ticketsystem.service;

import java.util.Date;
import java.util.List;

import com.example.ticketsystem.core.Comment;
import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.core.User;

public interface ITicketService {

	public Ticket saveorUpdateTicket(Ticket theTicket);
	
	public List<Ticket> getAllTickets();
	
	public List<Ticket> getTicketByFilter(Ticket theTicket);
	
	public Ticket getTicket(Long ticketId); 
	
	public User getUser(Long userId); 

	public Comment addComment(Comment comment);
	
	public List<Ticket> getTicketResolvedAfter30Days(Date date, Integer ticketStatus);
	
	public List<User> findByRole(Integer roleId);
	
	public List<Ticket> findByAssignedToIsNull();
	
	public List<Ticket> findByAssignedTo(User user);
	
}
