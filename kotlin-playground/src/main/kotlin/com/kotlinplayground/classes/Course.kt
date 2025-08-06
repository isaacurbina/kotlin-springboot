package org.example.com.kotlinplayground.classes

data class Course(
	val id: Int,
	val name: String,
	val author: String
)

fun main() {
	val course = Course(
		id = 1,
		name = "Reactive Programming in Modern Java using Project Reactor",
		author = "Dilip"
	)
	val course1 = Course(
		id = 2,
		name = "Reactive Programming in Modern Java using Project Reactor",
		author = "Dilip"
	)
	val course2 = course1.copy(id = 3, author = "Alex")
	println(course)
	println(course1)
	println(course2)
	println("Checking Object Equality: ${course == course1}")
}
