package com.example.ticketsystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.core.User;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {
	
	public List<Ticket> findByAssignedToOrOwnerOrStatus(User assignedTo, User Owner, Integer status);
	
	@Query("select t from TICKET t where t.statusUpdatedOn < :statusUpdatedOn and t.status = :status ")
	public List<Ticket> findByStatusUpdatedOnGreaterThanAndStatusResolved( @Param("statusUpdatedOn") Date statusUpdatedOn, @Param("status") Integer status );
	
	public List<Ticket> findByAssignedToIsNull();
	
	public List<Ticket> findByAssignedTo(User user);

}
