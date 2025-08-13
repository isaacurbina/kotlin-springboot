package org.example.com.kotlinplayground.collections

import org.example.com.kotlinplayground.dataset.Course
import org.example.com.kotlinplayground.dataset.CourseCategory
import org.example.com.kotlinplayground.dataset.courseList

private fun exploreFilter(
	courseList: List<Course>,
	predicate: (Course) -> Boolean
) {
	val developmentCourses = courseList
		.filter(predicate)
		.forEach { println("course: $it") }
	println("developmentCourses: $developmentCourses")
}

private fun exploreMap(
	courseList: List<Course>,
	predicate: (Course) -> Boolean
) {
	val courses = courseList
		.filter(predicate)
		.map { "${it.name} - ${it.category}" }
	println("courses: $courses")
}

fun main() {
	val courseList = courseList()
	val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPMENT }
	exploreFilter(courseList, devPredicate)

	println("---------------------------------------------------------------------")
	val designPredicate = { c: Course -> c.category == CourseCategory.DESIGN }
	exploreFilter(courseList, designPredicate)

	println("---------------------------------------------------------------------")
	exploreMap(courseList, devPredicate)
}
