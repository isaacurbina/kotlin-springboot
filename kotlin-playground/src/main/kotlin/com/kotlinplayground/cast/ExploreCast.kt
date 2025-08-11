package org.example.com.kotlinplayground.cast

import org.example.com.kotlinplayground.classes.Course

private fun checkType(any: Any) {
 	when (any) {
		is Course -> println("is Course: $any")
		is String -> println("is String: ${any.lowercase()}")
	 }
}

private fun castNumber(any: Any) {
	when (any) {
		any as? Double -> println("Value is Double: $any")
		any as? Float -> println("Value is Float: $any")
		any as? Int -> println("Value is Int: $any")
	}
}

fun main() {
	val course = Course(
		id = 1,
		name = "Reactive Programming in Modern Java using Project Reactor",
		author = "Dilip"
	)

	checkType(course)
	checkType("DILIP")
	castNumber(1.0)
	castNumber(2.0f)
	castNumber(3)
}
