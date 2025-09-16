package com.kotlinspring.course_catalog_service.dto

import jakarta.validation.constraints.NotBlank

data class InstructorDTO(
	val id: Int?,
	@get:NotBlank(message = "Instructor name must not be blank")
	var name: String
)
