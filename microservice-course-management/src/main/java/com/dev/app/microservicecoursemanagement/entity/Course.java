package com.dev.app.microservicecoursemanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7370367735280399294L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "publish_date")
	private LocalDate publishDate;
}
