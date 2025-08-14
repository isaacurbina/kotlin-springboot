package org.example.com.kotlinplayground.collections

import org.example.com.kotlinplayground.dataset.Course
import org.example.com.kotlinplayground.dataset.CourseCategory
import org.example.com.kotlinplayground.dataset.KAFKA
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

private fun exploreFlatMap(courseList: List<Course>, topic: String): List<String> {
	val topicCourses = courseList.flatMap { course ->
		val courseName = course.name
		course.topicsCovered
			.filter { it == topic }
			.map { courseName }
	}
	return topicCourses
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

	println("---------------------------------------------------------------------")
	val list = listOf(listOf(1, 2, 3), listOf(4, 5, 6))
	val mapResult = list.map { outerList ->
		outerList.map {
			it.toDouble()
		}
	}
	println("mapResult: $mapResult")
	val flatMapResult = list.flatMap { outerList ->
		outerList.map {
			it.toDouble()
		}
	}
	println("flatMapResult: $flatMapResult")

	val courses = exploreFlatMap(courseList, KAFKA)
	println("courses: $courses")
}
