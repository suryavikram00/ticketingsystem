package com.example.ticketsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketsystem.core.Ticket;
import com.example.ticketsystem.core.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	public List<User> findByRole(Integer roleId);
	
}
