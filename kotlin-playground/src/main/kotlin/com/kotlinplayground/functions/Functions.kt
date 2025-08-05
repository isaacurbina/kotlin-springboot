package org.example.com.kotlinplayground.functions

import java.time.LocalDate

fun main() {
	val unit = printName("Dilip")
	println("unit: $unit")

	val result = addition(1, 2)
	println("result: $result")

	val result1 = addition_approach1(1, 2)
	println("result1: $result1")

	printPersonDetails("Dilip", "abc@gmail.com", LocalDate.parse("2000-01-01"))
	printPersonDetails(name = "Dilip", dob = LocalDate.parse("2008-08-08"), email = "xyz@gmail.com")
}

private fun printName(name: String) {
	println("Name is: $name")
}

private fun addition(x: Int, y: Int): Int {
	return x + y
}

private fun addition_approach1(x: Int, y: Int): Int = x + y

private fun printPersonDetails(
	name: String,
	email: String = "",
	dob: LocalDate = LocalDate.now()
) {
	println("Name is $name and the email is $email and the dob is $dob")
}
