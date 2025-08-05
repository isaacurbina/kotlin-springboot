package org.example.com.kotlinplayground.functions

fun main() {
	val unit = printName("Dilip")
	println("unit: $unit")

	val result = addition(1, 2)
	println("result: $result")

	val result1 = addition_approach1(1, 2)
	println("result1: $result1")
}

private fun printName(name: String) {
	println("Name is: $name")
}

private fun addition(x: Int, y: Int): Int {
	return x + y
}

private fun addition_approach1(x: Int, y: Int): Int = x + y
