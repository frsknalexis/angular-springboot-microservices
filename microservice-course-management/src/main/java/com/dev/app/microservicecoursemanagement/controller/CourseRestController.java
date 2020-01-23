package com.dev.app.microservicecoursemanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.microservicecoursemanagement.entity.Transaction;
import com.dev.app.microservicecoursemanagement.intercomm.UserClient;
import com.dev.app.microservicecoursemanagement.service.CourseService;

@RestController
public class CourseRestController {

	@Autowired
	private UserClient userClient;
	
	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String serviceId;
	
	@GetMapping("/service/port")
	String getPort() {
		return "Service is working at port: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/service/instance")
	ResponseEntity<?> getInstances() {
		return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
	}
	
	@GetMapping("/service/user/{userId}")
	ResponseEntity<?> findTransactionsOfUser(@PathVariable(value = "userId") Long userId) {
		return ResponseEntity.ok(courseService.findTransactionsOfUser(userId));
	}
	
	@GetMapping("/service/all")
	ResponseEntity<?> findAllCourses() {
		return ResponseEntity.ok(courseService.allCourses());
	}
	
	@PostMapping("/service/enroll")
	ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
		transaction.setDateOfIssue(LocalDateTime.now());
		transaction.setCourse(courseService.findCourseById(transaction.getCourse().getId()));
		return new ResponseEntity<>(courseService.saveTransaction(transaction), HttpStatus.CREATED);
	}
	
	@GetMapping("/service/course/{courseId}")
	ResponseEntity<?> findStudentsOfCourse(@PathVariable(value = "courseId") Long courseId) {
		List<Transaction> transactions = courseService.findTransactionsOfCourse(courseId);
		
		if (CollectionUtils.isEmpty(transactions)) {
			return ResponseEntity.notFound().build();
		}
		List<Long> userIdList = transactions.parallelStream().map(t -> t.getUserId())
																.collect(Collectors.toList());
		
		List<String> students = userClient.getUserNames(userIdList);
		return ResponseEntity.ok(students);
	}
}
