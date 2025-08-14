package org.example.com.kotlinplayground.collections

import org.example.com.kotlinplayground.dataset.Course
import org.example.com.kotlinplayground.dataset.CourseCategory
import org.example.com.kotlinplayground.dataset.courseList

private fun exploreFilterUsingSequence(
	courseList: List<Course>,
	predicate: (Course) -> Boolean
) {
	val developmentCourses = courseList
		.asSequence()
		.filter(predicate)
		.forEach { println("course: $it") }
	println("developmentCourses: $developmentCourses")
}

fun main() {
	val namesListUsingSequence = listOf("Alex", "Ben", "Chloe")
		.asSequence()
		.filter { it.length >= 4 }
		.map { it.uppercase() }
		.toList()
	println("namesListUsingSequence: $namesListUsingSequence")

	val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPMENT }
	exploreFilterUsingSequence(courseList(), devPredicate)

	val range = 1..1_000_000_000

	range
		.asSequence()
		.map{ it.toDouble() }
		.take(40)
		.forEach {
			println("value is: $it")
		}
}
