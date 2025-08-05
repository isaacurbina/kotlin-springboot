package org.example.com.kotlinplayground.basics

fun main() {
	// if-else
	// when

	val name = "Alex"
	val result = if (name.length == 4) {
		println("Name is Four Characters")
	} else println("Name is not Four Characters")

	println("result: $result")

	// 1-> Gold, 2-> Silver, 3-> Bronze

	val position = 1

	val medal = when (position) {
		1 -> "GOLD"
		2 -> "SILVER"
		3 -> "BRONZE"
		else -> "NO MEDAL"
	}

	println("medal: $medal")

	val medal2 = if (position == 1) {
		"GOLD"
	} else if (position == 2) {
		"SILVER"
	} else if (position == 3) {
		"BRONZE"
	} else "NO MEDAL"

	println("medal2: $medal2")
}
