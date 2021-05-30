package com.example.ticketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketsystem.core.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
