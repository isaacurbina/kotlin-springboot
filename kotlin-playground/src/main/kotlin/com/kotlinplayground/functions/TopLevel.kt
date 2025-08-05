package org.example.com.kotlinplayground.functions

const val courseName = "Kotlin Programming"

fun main() {
	val num = topLevelFunction()
	println("Num is: $num")
}

fun topLevelFunction() : Int {
	return (0..100).random()
}
