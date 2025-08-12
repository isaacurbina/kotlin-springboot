package org.example.com.kotlinplayground.collections

fun main() {
	val names = listOf("Alex", "Ben", "Chloe")
	println("Names: $names")

	val namesMutable = mutableListOf("Alex", "Ben", "Chloe")
	println("namesMutable: $namesMutable")
	namesMutable.add("Adam")
	println("namesMutable: $namesMutable")

	val set = setOf("Alex", "Ben", "Chloe")
	println("set: $set")

	val setMutable = mutableSetOf("Alex", "Ben", "Chloe")
	println("setMutable: $setMutable")
	setMutable.add("Adam")
	println("setMutable: $setMutable")

	val map = mapOf("Dilip" to 34, "Jack" to 2, "Jill" to 32)
	println("map: $map")

	val mapMutable = mutableMapOf("Dilip" to 34, "Jack" to 2, "Jill" to 32)
	println("mapMutable: $mapMutable")
	mapMutable["Adam"] = 8
	println("mapMutable: $mapMutable")
}
