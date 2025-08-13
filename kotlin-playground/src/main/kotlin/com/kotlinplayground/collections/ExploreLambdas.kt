package org.example.com.kotlinplayground.collections

fun main() {
	val addLambda = { x: Int, y: Int -> x + y }
	val addResult = addLambda(3, 4)
	println("addResult: $addResult")

	val multiplyLambda = { x: Int, y: Int ->
		println("x: $x, y: $y")
		x * y 
	}
	val multiplyResult = multiplyLambda(3, 4)
	println("multiplyResult: $multiplyResult")
}
