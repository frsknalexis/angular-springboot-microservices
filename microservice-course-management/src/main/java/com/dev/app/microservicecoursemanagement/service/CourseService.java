package com.dev.app.microservicecoursemanagement.service;

import java.util.List;

import com.dev.app.microservicecoursemanagement.entity.Course;
import com.dev.app.microservicecoursemanagement.entity.Transaction;

public interface CourseService {

	List<Course> allCourses();
	
	Course findCourseById(Long courseId);
	
	List<Transaction> findTransactionsOfUser(Long userId);
	
	List<Transaction> findTransactionsOfCourse(Long courseId);
	
	Transaction saveTransaction(Transaction transaction);
	
}
