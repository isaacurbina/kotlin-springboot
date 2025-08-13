package org.example.com.kotlinplayground.collections

import org.example.com.kotlinplayground.dataset.Course
import org.example.com.kotlinplayground.dataset.CourseCategory
import org.example.com.kotlinplayground.dataset.courseList

private fun exploreFilter(
	courseList: List<Course>,
	predicate: (Course) -> Boolean
) {
	val developmentCourses = courseList
		.filter { predicate(it) }
		.forEach { println("course: $it") }
	println("developmentCourses: $developmentCourses")
}

fun main() {
	val courseList = courseList()
	val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPEMENT }
	val designPredicate = { c: Course -> c.category == CourseCategory.DESIGN }
	exploreFilter(courseList, devPredicate)
	exploreFilter(courseList, designPredicate)
}
