package com.kotlinspring.course_catalog_service.repository

import com.kotlinspring.course_catalog_service.util.courseEntityList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test

@DataJpaTest
@ActiveProfiles("test")
class CourseRepositoryIntgTest {

	@Autowired
	lateinit var courseRepository: CourseRepository

	@BeforeEach
	fun setUp() {
		courseRepository.deleteAll()
		val courses = courseEntityList()
		courseRepository.saveAll(courses)
	}

	@Test
	fun findByNameContaining() {
		val courses = courseRepository.findByNameContaining("SpringBoot")
		println("courses: $courses")
		assertEquals(2, courses.size)
	}
}
