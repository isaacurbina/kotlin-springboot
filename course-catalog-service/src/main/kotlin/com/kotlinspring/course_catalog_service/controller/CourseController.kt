package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(
	private val courseService: CourseService
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun addCourse(
		@RequestBody @Valid courseDTO: CourseDTO
	): CourseDTO {
		return courseService.addCourse(courseDTO)
	}

	@GetMapping
	fun retrieveAllCourses(): List<CourseDTO> {
		return courseService.retrieveAllCourses()
	}

	@PutMapping("/{course_id}")
	fun updateCourse(
		@RequestBody courseDTO: CourseDTO,
		@PathVariable("course_id") courseId: Int
	): CourseDTO {
		return courseService.updateCourse(courseId, courseDTO)
	}

	@DeleteMapping("/{course_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteCourse(
		@PathVariable("course_id") courseId: Int
	) {
		courseService.deleteCourse(courseId)
	}
}
