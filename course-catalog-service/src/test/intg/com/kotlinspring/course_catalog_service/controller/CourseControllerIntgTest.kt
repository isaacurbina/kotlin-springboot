package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

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
}
