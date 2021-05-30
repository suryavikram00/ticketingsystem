package com.example.ticketsystem.service;

public interface ITicketAssignmentService {
	
	public void assignTicketToAgent(Long TicketId);
	
	public void removeAgent(Long agentId);
	
	public void addAgent(Long agentId);

}
