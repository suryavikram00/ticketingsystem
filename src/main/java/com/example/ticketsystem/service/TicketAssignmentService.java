package com.example.ticketsystem.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.core.User;

public class TicketAssignmentService implements ITicketAssignmentService {

	private Integer noOfAgents;

	private static final Integer NODE_REPLICATION_FACTOR = 3;

	private List<Long> agentBucket;

	private Map<User, Boolean> assignedAgents;

	private List<User> agentList;

	@Autowired
	private ITicketService ticketService;

	public TicketAssignmentService() {

		// get all agents from the system and assign it to agentList

		// the number of agents will be returned from service
		this.noOfAgents = 5;

		// update the agentList map with value false by default
		this.buildAssignedAgent();
	}

	private void buildAssignedAgent() {
		for (User eachAgent : agentList) {
			this.assignedAgents.put(eachAgent, Boolean.FALSE);
		}
	}

	private void buildTicketBucketMap() {
		this.agentBucket = new LinkedList<>();
		for (int index = 0; index < noOfAgents * (noOfAgents * NODE_REPLICATION_FACTOR); index++) {
			// get random agent
			User agent = this.getRandomAgentNode();
			this.agentBucket.add(agent.getUserId());
		}
	}

	private User getRandomAgentNode() {
		if (assignedAgents.isEmpty()) {
			// rebuild the map
			this.buildAssignedAgent();
		}

		// get random agent
		int randomNumber = new Random().nextInt(assignedAgents.size());

		User agent = this.agentList.get(randomNumber);

		// remove the agent from the agent Node
		this.assignedAgents.remove(agent);

		// return the random agent
		return agent;

	}

	@Override
	public void assignTicketToAgent(Long ticketId) {
		Integer bucketValue = (int) ((ticketId * noOfAgents) - 1);
		Integer modOfBucketValue = bucketValue % (noOfAgents * NODE_REPLICATION_FACTOR);
		Long agentId = agentBucket.get(modOfBucketValue);
		Ticket ticket = ticketService.getTicket(ticketId);
		User agent = ticketService.getUser(agentId);
		if (ticket != null && ticket.getTicketId() != null && agent != null && agent.getUserId() != null) {
			ticket.setAssignedTo(agent);
			ticketService.saveorUpdateTicket(ticket);
		}

	}

	@Override
	public void removeAgent(Long agentId) {
		this.noOfAgents--;
		this.buildTicketBucketMap();
		// get all tickets assigned to the particular agent
		List<Ticket> assignedTicketForThisAgent = new LinkedList<>();

		// call assignTicketToAgent() method with the ticketId
		for (Ticket eachTicket : assignedTicketForThisAgent) {
			this.assignTicketToAgent(eachTicket.getTicketId());
		}
	}

	@Override
	public void addAgent(Long agentId) {
		this.noOfAgents++;
		this.buildTicketBucketMap();

		// get all unassigned tickets
		List<Ticket> allUnassignedTicket = new LinkedList<>();

		// call assignTicketToAgent() method with the ticketId
		for (Ticket eachTicket : allUnassignedTicket) {
			this.assignTicketToAgent(eachTicket.getTicketId());
		}

	}

}
