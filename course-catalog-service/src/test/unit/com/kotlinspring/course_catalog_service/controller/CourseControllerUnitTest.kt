package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Autowired
	lateinit var courseServiceMock: CourseService
}
