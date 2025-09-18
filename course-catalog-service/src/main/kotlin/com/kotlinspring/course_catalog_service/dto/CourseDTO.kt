package com.kotlinspring.course_catalog_service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CourseDTO(
	val id: Int?,
	@get:NotBlank(message = "Course name must not be blank")
	val name: String,
	@get:NotBlank(message = "Course category must not be blank")
	val category: String,
	@get:NotNull(message = "Course instructorId must not be blank")
	var instructorId: Int? = null
)
