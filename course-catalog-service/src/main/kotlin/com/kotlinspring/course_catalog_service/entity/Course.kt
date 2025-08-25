package com.kotlinspring.course_catalog_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Courses")
data class Course(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Int? = null,
	val name: String = "",
	val category: String = ""
)
