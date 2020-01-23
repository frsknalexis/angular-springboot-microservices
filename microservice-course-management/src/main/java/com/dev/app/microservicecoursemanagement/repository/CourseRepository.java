package com.dev.app.microservicecoursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.app.microservicecoursemanagement.entity.Course;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

	
}
