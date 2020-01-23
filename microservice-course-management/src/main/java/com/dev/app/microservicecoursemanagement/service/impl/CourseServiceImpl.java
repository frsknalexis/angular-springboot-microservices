package com.dev.app.microservicecoursemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev.app.microservicecoursemanagement.entity.Course;
import com.dev.app.microservicecoursemanagement.entity.Transaction;
import com.dev.app.microservicecoursemanagement.repository.CourseRepository;
import com.dev.app.microservicecoursemanagement.repository.TransactionRepository;
import com.dev.app.microservicecoursemanagement.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	@Qualifier("courseRepository")
	private CourseRepository courseRepository;
	
	@Autowired
	@Qualifier("transactionRepository")
	private TransactionRepository transactionRepository;

	@Override
	public List<Course> allCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course findCourseById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	}

	@Override
	public List<Transaction> findTransactionsOfUser(Long userId) {
		return transactionRepository.findAllByUserId(userId);
	}

	@Override
	public List<Transaction> findTransactionsOfCourse(Long courseId) {
		return transactionRepository.findAllByCourseId(courseId);
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
