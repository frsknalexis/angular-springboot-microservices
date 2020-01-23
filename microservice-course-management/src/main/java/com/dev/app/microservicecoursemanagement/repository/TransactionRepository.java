package com.dev.app.microservicecoursemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.app.microservicecoursemanagement.entity.Transaction;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByUserId(Long userId);
	
	List<Transaction> findAllByCourseId(Long courseId);
	
}
