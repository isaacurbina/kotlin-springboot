package org.example.com.kotlinplayground.basics

import org.example.com.kotlinplayground.functions.courseName
import org.example.com.kotlinplayground.functions.topLevelFunction

fun main() {
	val name = "Dilip"
	println(name)

	var age = 34
	println(age)
	age = 35
	println(age)

	val salary = 30000L
	println(salary)

	val course = "Kotlin Spring"
	println("course: $course and the course length is ${course.length}")

	val multiline = "ABC \nDEF"
	println(multiline)

	val multiline1 = """
		ABC
		DEF
	""".trimIndent()
	println(multiline1)

	val num = topLevelFunction()
	println("Num is: $num")
	println("courseName: $courseName")
}
