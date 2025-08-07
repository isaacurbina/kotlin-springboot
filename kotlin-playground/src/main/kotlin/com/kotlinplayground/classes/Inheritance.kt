package org.example.com.kotlinplayground.classes

open class User(val name: String) {

	open var isLoggedIn: Boolean = false
	open fun login() {
		println("Inside Login")
	}
}

class Student(name: String): User(name = name) {

	override var isLoggedIn: Boolean = false
	override fun login() {
		println("Inside Student Login")
		super.login()
	}
}

class Instructor(name: String): User(name = name) {

	override var isLoggedIn: Boolean = true
	override fun login() {
		println("Inside Instructor Login")
		super.login()
	}
}

fun main() {
	val student = Student("Alex")
	println("Student name is: ${student.name}")
	student.login()
	student.isLoggedIn = true
	println("Logged in value is: ${student.isLoggedIn}")

	val instructor = Instructor("Dilip")
	println("Instructor name is: ${instructor.name}")
	instructor.login()
}
