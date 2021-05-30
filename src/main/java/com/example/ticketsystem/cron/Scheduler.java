package com.example.ticketsystem.cron;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.enumtype.TicketStatus;
import com.example.ticketsystem.service.ITicketService;

@Component
public class Scheduler {
	
	@Autowired
	private ITicketService ticketService;

	@Scheduled(cron = "0 0 23 * * *")
	public void currentTime() {
		System.out.println("In scheduler");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Date dateBefore30Days = cal.getTime();
		System.out.println(dateBefore30Days);		
		List<Ticket> ticketList = ticketService.getTicketResolvedAfter30Days(dateBefore30Days, TicketStatus.RESOLVED.ordinal());
		for(Ticket eachTicket : ticketList){
			eachTicket.setStatus(TicketStatus.CLOSED.ordinal());
			ticketService.saveorUpdateTicket(eachTicket);
		}		
	}
}
