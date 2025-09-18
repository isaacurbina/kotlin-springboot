package com.kotlinspring.course_catalog_service.service

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.entity.Course
import com.kotlinspring.course_catalog_service.exception.CourseNotFoundException
import com.kotlinspring.course_catalog_service.exception.InstructorNotValidException
import com.kotlinspring.course_catalog_service.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(
	private val courseRepository: CourseRepository,
	private val instructorService: InstructorService
) {

	companion object : KLogging()

	fun addCourse(courseDTO: CourseDTO): CourseDTO {
		val instructor = instructorService.findByInstructorId(courseDTO.instructorId!!)

		if (!instructor.isPresent) {
			throw InstructorNotValidException("Instructor not found for id: ${courseDTO.instructorId}")
		}

		val entity = courseDTO.let {
			Course(
				id = null,
				name = it.name,
				category = it.category,
				instructor = instructor.get()
			)
		}
		courseRepository.save(entity)
		logger.info("Saved course is: $entity")

		return entity.let {
			CourseDTO(
				id = it.id,
				name = it.name,
				category = it.category,
				instructorId = it.instructor!!.id
			)
		}
	}

	fun retrieveAllCourses(courseName: String?): List<CourseDTO> {
		logger.info("retrieveAllCourses() called: courseName: $courseName")

		val courses = courseName?.let {
			courseRepository.findCoursesByName(courseName)
		} ?: courseRepository.findAll()

		return courses
			.map {
				logger.info("course is: $it")
				CourseDTO(
					id = it.id,
					name = it.name,
					category = it.category
				)
			}
	}

	fun updateCourse(
		courseId: Int,
		courseDTO: CourseDTO
	): CourseDTO {
		logger.info("updateCourse() called for $courseId")
		val course = courseRepository.findById(courseId)

		return if (course.isPresent) {
			course.get().let {
				it.name = courseDTO.name
				it.category = courseDTO.category
				courseRepository.save(it)
				logger.info("updateCourse() -> Saved course is: $it")

				it.let {
					CourseDTO(
						id = it.id,
						name = it.name,
						category = it.category
					)
				}
			}

		} else {
			throw CourseNotFoundException("No course found for id : $courseId")
		}
	}

	fun deleteCourse(courseId: Int) {
		logger.info("deleteCourse() called for $courseId")
		val course = courseRepository.findById(courseId)

		return if (course.isPresent) {
			course.get().let {
				courseRepository.deleteById(courseId)
				logger.info("deleteCourse() -> Deleted course is: $it")
			}

		} else {
			throw CourseNotFoundException("No course found for id : $courseId")
		}
	}
}
