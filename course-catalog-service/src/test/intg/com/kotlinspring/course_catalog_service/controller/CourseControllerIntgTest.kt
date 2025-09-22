package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.dto.CourseDTO
import com.kotlinspring.course_catalog_service.entity.Course
import com.kotlinspring.course_catalog_service.repository.CourseRepository
import com.kotlinspring.course_catalog_service.repository.InstructorRepository
import com.kotlinspring.course_catalog_service.util.courseEntityList
import com.kotlinspring.course_catalog_service.util.instructorEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Testcontainers
class CourseControllerIntgTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Autowired
	lateinit var courseRepository: CourseRepository

	@Autowired
	lateinit var instructorRepository: InstructorRepository

	@BeforeEach
	fun setUp() {
		courseRepository.deleteAll()
		instructorRepository.deleteAll()

		val instructor = instructorEntity()
		instructorRepository.save(instructor)

		val courses = courseEntityList()
		courseRepository.saveAll(courses)
	}

	@Test
	fun addCourse() {
		val instructor = instructorRepository.findAll().first()

		val course = CourseDTO(
			id = null,
			name = "Build RestFul APis using SpringBoot and Kotlin",
			category = "Development",
			instructorId = instructor.id
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
	fun retrieveAllCoursesByName() {
		val uri = UriComponentsBuilder.fromUriString("/v1/courses")
			.queryParam("course_name", "SpringBoot")
			.toUriString()

		val courses = webTestClient
			.get()
			.uri(uri)
			.exchange()
			.expectStatus()
			.isOk
			.expectBodyList(CourseDTO::class.java)
			.returnResult()
			.responseBody

		println("courseDTOs: $courses")
		assertEquals(2, courses!!.size)
	}

	@Test
	fun updateCourse() {
		val instructor = instructorRepository.findAll().first()

		// existing course
		val course = Course(
			id = null,
			name = "Build RestFul APis using SpringBoot and Kotlin",
			category = "Development",
			instructor = instructor
		)
		val savedCourse = courseRepository.save(course)

		// updated course
		val updatedCourse = CourseDTO(
			id = null,
			name = "Build RestFul APis using SpringBoot and Kotlin1",
			category = "Development",
			instructorId = instructor.id
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

	companion object {

		@Container
		val postgresDB =
			PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:13-alpine")).apply {
				withDatabaseName("testdb")
				withUsername("postgres")
				withPassword("secret")
			}

		@JvmStatic
		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", postgresDB::getJdbcUrl)
			registry.add("spring.datasource.username", postgresDB::getUsername)
			registry.add("spring.datasource.password", postgresDB::getPassword)
		}
	}
}
