package com.example.ticketsystem.utils;

import java.io.IOException;

import com.example.ticketsystem.core.Comment;
import com.example.ticketsystem.core.Ticket;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class EmailSenderUtil {
	
	private static final String EMAIL_FROM = "yogesh@sinecycle.com";
	private static final String SEND_GRID_API_KEY = "SG.bQpn5_GET52POyrNNjto5w.WxTxFJLLm3DmhNNHdwKdj6Nw AVhFd49AmIiN1HN8qjU ";
	

	public static void sendEmailThroughSendGrid(Comment comment, Ticket ticket){
		Email from = new Email(EMAIL_FROM);
	    String subject = "Agent has replied to your ticketId #" + ticket.getTicketId();
	    
	    Email to = new Email(ticket.getOwner().getEmail());
	    Content content = new Content("text/plain", comment.getComment());
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(SEND_GRID_API_KEY);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      System.out.println(ex);
	    }		
	}
}
