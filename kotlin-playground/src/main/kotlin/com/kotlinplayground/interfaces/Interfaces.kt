package org.example.com.kotlinplayground.interfaces

import org.example.com.kotlinplayground.classes.Course

interface CourseRepository {

	var isCoursePersisted: Boolean

	fun getById(id: Int) : Course

	fun save(course: Course) : Int {
		println("course: $course")
		return course.id
	}
}

interface Repository {
	fun getAll(): Any
}

interface A {
	fun doSomething() {
		println("doSomething in A")
	}
}

interface B {
	fun doSomething() {
		println("doSomething in B")
	}
}

class SqlCourseRepository : CourseRepository, Repository {

	override var isCoursePersisted: Boolean = false

	override fun getById(id: Int): Course {
		return Course(id = id, name = "Reactive Programming in Modern Java using Project Reactor", author = "Dilip")
	}

	override fun save(course: Course): Int {
		println("course in SqlCourseRepository: $course")
		isCoursePersisted = true
		return super.save(course)
	}

	override fun getAll(): Any {
		return 1
	}
}

class NoSqlCourseRepository : CourseRepository {

	override var isCoursePersisted: Boolean = false

	override fun getById(id: Int): Course {
		return Course(id = id, name = "Spring Boot in 30 Minutes", author = "Dilip")
	}

	override fun save(course: Course): Int {
		println("course in NoSqlCourseRepository: $course")
		isCoursePersisted = true
		return super.save(course)
	}
}

class AB: A, B {
	override fun doSomething() {
		super<A>.doSomething()
		super<B>.doSomething()
		println("doSomething in AB")
	}
}

fun main() {
	val sqlCourseRepository = SqlCourseRepository()
	val course = sqlCourseRepository.getById(2)
	println("Course is: $course")
	val courseId = sqlCourseRepository.save(course)
	println("Saved Course Id is: $courseId")
	val result = sqlCourseRepository.getAll()
	println("Result is: $result")
	println("Course persisted value is: ${sqlCourseRepository.isCoursePersisted}")

	val noSqlCourseRepository = NoSqlCourseRepository()
	val course1 = noSqlCourseRepository.getById(5)
	println("Course is: $course1")
	val courseId1 = noSqlCourseRepository.save(course1)
	println("Saved Course Id is: $courseId1")
	println("Course persisted value is: ${noSqlCourseRepository.isCoursePersisted}")

	val ab = AB()
	ab.doSomething()
}
