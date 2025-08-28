package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.service.CourseService
import com.kotlinspring.course_catalog_service.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

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
}
