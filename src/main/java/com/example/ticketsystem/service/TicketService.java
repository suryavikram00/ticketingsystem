package com.example.ticketsystem.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ticketsystem.core.Comment;
import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.core.User;
import com.example.ticketsystem.repository.ICommentRepository;
import com.example.ticketsystem.repository.ITicketRepository;
import com.example.ticketsystem.repository.IUserRepository;
import com.example.ticketsystem.utils.EmailSenderUtil;

@Service
public class TicketService implements ITicketService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITicketRepository ticketRepository;

	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional
	public Ticket saveorUpdateTicket(Ticket theTicket) {
		Ticket ticket = null;
		try {
			if (theTicket.getTicketId() == null) {
				theTicket.setStatusUpdatedOn(new Date());
			} else {
				Optional<Ticket> ticketOptional = ticketRepository.findById(theTicket.getTicketId());
				Ticket ticketFromDB = ticketOptional.isPresent() ? ticketOptional.get() : null;
				ticketFromDB.setStatusUpdatedOn(ticketFromDB.getStatus().equals(theTicket.getStatus())
						? ticketFromDB.getStatusUpdatedOn() : new Date());
				ticketFromDB.setAssignedTo(
						theTicket.getAssignedTo() != null && theTicket.getAssignedTo().getUserId() != null
								? theTicket.getAssignedTo() : ticketFromDB.getAssignedTo());
				ticketFromDB.setStatus(ticketFromDB.getStatus().equals(theTicket.getStatus()) ? ticketFromDB.getStatus()
						: theTicket.getStatus());
				theTicket = ticketFromDB;
			}
			ticket = ticketRepository.save(theTicket);
			logger.info("Successfully saved or updated ticket with id :: " + ticket.getTicketId());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return ticket;
	}

	@Override
	@Transactional(readOnly = false)
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		try {
			ticketList = ticketRepository.findAll();
			logger.info("Successfully fetched ticket with size :: " + ticketList.size());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return ticketList;

	}

	@Override
	@Transactional(readOnly = false)
	public List<Ticket> getTicketByFilter(Ticket theTicket) {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		try {
			Example<Ticket> example = Example.of(theTicket);
			ticketList = ticketRepository.findAll(example);
			logger.info("Successfully fetched ticket with size :: " + ticketList.size());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return ticketList;
	}

	@Override
	@Transactional(readOnly = false)
	public Ticket getTicket(Long ticketId) {
		Ticket ticket = null;
		try {
			Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
			ticket = ticketOptional.isPresent() ? ticketOptional.get() : null;
			logger.info("Successfully fetched ticket :: " + ticket);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return ticket;
	}

	@Override
	@Transactional
	public Comment addComment(Comment comment) {
		Comment savedComment = null;
		try {
			comment.setCommentedOn(new Date());
			savedComment = commentRepository.save(comment);
			logger.info("Successfully Saved  comment :: " + savedComment.getCommentId());
			Ticket ticket = this.getTicket(comment.getTicket().getTicketId());
		    if(ticket != null ){
		    	// comment.getCommentedBy().getRoleId() == 1 (agent), then send a email to the customer
		    	EmailSenderUtil.sendEmailThroughSendGrid(savedComment, ticket);
		    	logger.info("Successfully send email to the customer for commentid :: " + savedComment.getCommentId());
		    }			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return savedComment;
	}	
	

	@Override
	public List<Ticket> getTicketResolvedAfter30Days(Date date, Integer ticketStatus) {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		try{			
			ticketList = ticketRepository.findByStatusUpdatedOnGreaterThanAndStatusResolved(date, ticketStatus);
			logger.info("Successfully fetched resolved tickets greater than 30 days | size :: " + ticketList.size());
		} catch (Exception e){
			logger.info(e.toString());
		}
		return ticketList;
	}

	@Override
	public User getUser(Long userId) {
		User user = null;
		try {
			Optional<User> userOptional = userRepository.findById(userId);
			user = userOptional.isPresent() ? userOptional.get() : null;
			logger.info("Successfully fetched user :: " + user.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return user;
	}
	
	
	
}
