package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.entity.Course
import com.kotlinspring.course_catalog_service.service.CourseService
import com.kotlinspring.course_catalog_service.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@MockkBean
	lateinit var courseServiceMock: CourseService

	@Test
	fun addCourse() {
		val course = CourseDTO(
			id = null,
			name = "Build RestFul APis using SpringBoot and Kotlin",
			category = "Development"
		)

		every { courseServiceMock.addCourse(any()) } returns courseDTO(id = 1)

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
	fun addCourse_validation() {
		val course = CourseDTO(
			id = null,
			name = "",
			category = ""
		)

		every { courseServiceMock.addCourse(any()) } returns courseDTO(id = 1)

		val response = webTestClient
			.post()
			.uri("/v1/courses")
			.bodyValue(course)
			.exchange()
			.expectStatus().isBadRequest
			.expectBody(String::class.java)
			.returnResult()
			.responseBody

		assertEquals("Course category must not be blank, Course name must not be blank", response)
	}

	@Test
	fun retrieveAllCourses() {
		every { courseServiceMock.retrieveAllCourses() }.returnsMany(
			listOf(
				courseDTO(id = 1),
				courseDTO(id = 2),
				courseDTO(id = 3)
			)
		)

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
		val courseDTO = CourseDTO(
			100,
			"Build RestFul APis using SpringBoot and Kotlin", "Development"
		)
		every { courseServiceMock.updateCourse(any(), any()) } returns courseDTO(
			id = 100,
			name = "Build RestFul APis using SpringBoot and Kotlin1",
			category = "Development"
		)

		// updated course
		val updatedCourse = Course(
			null,
			"Build RestFul APis using SpringBoot and Kotlin1", "Development"
		)

		val savedUpdatedCourse = webTestClient
			.put()
			.uri("/v1/courses/{courseId}", courseDTO.id)
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
		every { courseServiceMock.deleteCourse(any()) } just runs

		webTestClient
			.delete()
			.uri("/v1/courses/{courseId}", 1)
			.exchange()
			.expectStatus().isNoContent
	}
}
