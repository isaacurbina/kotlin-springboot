package org.example.com.kotlinplayground.exceptions

private fun nameLength(name: String?): Int? {
	val result = try {
		name!!.length
	} catch (e: Exception) {
		println("Exception is: $e")
		null
	}
	return result
}

private fun returnNothing(): Nothing {
	throw Exception("This function returns nothing")
}

fun main() {
	val nameLength = nameLength("Dilip")
	println("nameLength: $nameLength")

	val nullableNameLength = nameLength(null)
	println("nullableNameLength: $nullableNameLength")

	returnNothing()
}
