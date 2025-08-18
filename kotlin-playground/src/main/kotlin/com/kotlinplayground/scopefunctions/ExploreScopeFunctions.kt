package org.example.com.kotlinplayground.scopefunctions

import org.example.com.kotlinplayground.classes.Course
import org.example.com.kotlinplayground.classes.CourseCategory

fun main() {
	exploreApply()
	exploreAlso()
}

private fun exploreApply() {
	val course = Course(
		id = 1,
		name = "Design Thinking in Kotlin",
		author = "Dilip"
	).apply {
		this.courseCategory = CourseCategory.DESIGN
	}

	println("course: $course")
}

private fun exploreAlso() {
	Course(
		id = 1,
		name = "Design Thinking in Kotlin",
		author = "Dilip"
	).also {
		println("Course is $it")
	}
}
