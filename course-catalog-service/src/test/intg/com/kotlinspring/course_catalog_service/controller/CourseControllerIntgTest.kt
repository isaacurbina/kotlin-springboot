package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.entity.Course
import com.kotlinspring.course_catalog_service.repository.CourseRepository
import com.kotlinspring.course_catalog_service.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Autowired
	lateinit var courseRepository: CourseRepository

	@BeforeEach
	fun setUp() {
		courseRepository.deleteAll()
		val courses = courseEntityList()
		courseRepository.saveAll(courses)
	}

	@Test
	fun addCourse() {
		val course = CourseDTO(
			id = null,
			name = "Build RestFul APis using SpringBoot and Kotlin",
			category = "Development"
		)

		val savedCourse = webTestClient
			.post()
			.uri("/v1/courses")
			.bodyValue(course)
			.exchange()
			.expectStatus().isCreated
			.expectBody(CourseDTO::class.java)
			.returnResult()
			.responseBody

		Assertions.assertTrue { savedCourse!!.id != null }
	}

	@Test
	fun retrieveAllCourses() {
		val courses = webTestClient
			.get()
			.uri("/v1/courses")
			.exchange()
			.expectStatus()
			.isOk
			.expectBodyList(CourseDTO::class.java)
			.returnResult()
			.responseBody

		println("courseDTOs: $courses")
		assertEquals(3, courses!!.size)
	}

	@Test
	fun updateCourse() {
		// existing course
		val course = Course(
			null,
			"Build RestFul APis using SpringBoot and Kotlin", "Development"
		)
		val savedCourse = courseRepository.save(course)

		// updated course
		val updatedCourse = Course(
			null,
			"Build RestFul APis using SpringBoot and Kotlin1", "Development"
		)

		val savedUpdatedCourse = webTestClient
			.put()
			.uri("/v1/courses/{courseId}", savedCourse.id)
			.bodyValue(updatedCourse)
			.exchange()
			.expectStatus().isOk
			.expectBody(CourseDTO::class.java)
			.returnResult()
			.responseBody

		assertEquals("Build RestFul APis using SpringBoot and Kotlin1", savedUpdatedCourse!!.name)
	}

	@Test
	fun deleteCourse() {
		// existing course
		val course = Course(
			null,
			"Build RestFul APis using SpringBoot and Kotlin", "Development"
		)
		val savedCourse = courseRepository.save(course)

		webTestClient
			.delete()
			.uri("/v1/courses/{courseId}", savedCourse.id)
			.exchange()
			.expectStatus().isNoContent
	}
}
