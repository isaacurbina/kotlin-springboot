package org.example.com.kotlinplayground.collections

fun calculate(x: Int, y: Int, op: (x: Int, y: Int) -> Int): Int {
	return op(x, y)
}

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

	val result = calculate(2, 3) { a, b -> a * b }
	println("result: $result")

	val result2 = calculate(2, 3) { a, b -> a + b }
	println("result2: $result2")
}
