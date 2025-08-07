package org.example.com.kotlinplayground.classes

open class User(val name: String) {
	fun login() {
		println("Inside Login")
	}
}

class Student(name: String): User(name = name)

class Instructor(name: String): User(name = name)

fun main() {
	val student = Student("Alex")
	println("Student name is: ${student.name}")
	student.login()

	val instructor = Instructor("Dilip")
	println("Instructor name is: ${instructor.name}")
	instructor.login()
}
