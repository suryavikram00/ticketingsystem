package com.example.ticketsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketsystem.core.Comment;
import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.service.TicketService;

@RestController
public class TicketController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value = "/ticket", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createTicket(@RequestBody Ticket theTicket, HttpServletRequest request, HttpServletResponse response){
		Ticket savedTicket = ticketService.saveorUpdateTicket(theTicket);
		return new ResponseEntity<String>(savedTicket != null ? "Ticket Created Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Ticket>> getAllTickets(HttpServletRequest request, HttpServletResponse response){
		List<Ticket> ticketList = ticketService.getAllTickets(); 
		return new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/ticket/filter", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Ticket>> getTicketsByFilter(@RequestBody Ticket theTicket, HttpServletRequest request, HttpServletResponse response){
		List<Ticket> ticketList = ticketService.getTicketByFilter(theTicket);
		return new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket/{ticketId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId, HttpServletRequest request, HttpServletResponse response){
		Ticket ticket = ticketService.getTicket(ticketId); 
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/ticket", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> editTicketById(@RequestBody Ticket theTicket, HttpServletRequest request, HttpServletResponse response){
		Ticket updatedTicket = ticketService.saveorUpdateTicket(theTicket);
		return new ResponseEntity<String>(updatedTicket != null ? "Ticket Updated Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket/assign-agent", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> assignAgentToTicket(@RequestBody Ticket theTicket, HttpServletRequest request, HttpServletResponse response){
		Ticket updatedTicket = ticketService.saveorUpdateTicket(theTicket);
		return new ResponseEntity<String>(updatedTicket != null ? "Ticket Updated Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket/update-status", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateTicketStatus(@RequestBody Ticket theTicket, HttpServletRequest request, HttpServletResponse response){
		Ticket updatedTicket = ticketService.saveorUpdateTicket(theTicket);
		return new ResponseEntity<String>(updatedTicket != null ? "Ticket Status Updated Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket/{ticketId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId, HttpServletRequest request, HttpServletResponse response){
		Ticket ticket = ticketService.getTicket(ticketId); 
		Ticket deletedTicket = null;
		if(ticket != null){
			ticket.setTicketType(Boolean.FALSE);
			deletedTicket = ticketService.saveorUpdateTicket(ticket);
		}		
		return new ResponseEntity<String>(deletedTicket != null ? "Ticket Deleted Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ticket/add-comment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addComment(@RequestBody Comment theComment, HttpServletRequest request, HttpServletResponse response){
		Comment savedComment = ticketService.addComment(theComment);
		return new ResponseEntity<String>(savedComment != null ? "Comment added Successfully!" : "Something went wrong!", HttpStatus.OK);
	}
}
