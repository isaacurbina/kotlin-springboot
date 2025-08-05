package org.example.com.kotlinplayground.basics

fun main() {
	val range = 1..10
	for (i in range) {
		println("i: $i")
	}

	val reverseRange = 10 downTo 1
	for (i in reverseRange) {
		println("reverseRange: $i")
	}

	for (i in reverseRange step 2) {
		println("reverseRange step 2: $i")
	}
}
