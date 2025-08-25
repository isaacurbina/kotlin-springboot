package com.kotlinspring.course_catalog_service.service

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.entity.Course
import com.kotlinspring.course_catalog_service.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(
	private val courseRepository: CourseRepository
) {

	companion object : KLogging()

	fun addCourse(courseDTO: CourseDTO): CourseDTO {
		val entity = courseDTO.let {
			Course(null, it.name, it.category)
		}
		courseRepository.save(entity)
		logger.info("Saved course is: $entity")

		return entity.let {
			CourseDTO(it.id, it.name, it.category)
		}
	}

	fun retrieveAllCourses(): List<CourseDTO> {
		logger.info("retrieveAllCourses() called")
		return courseRepository.findAll()
			.map {
				logger.info("course is: $it")
				CourseDTO(it.id, it.name, it.category)
			}
	}
}
