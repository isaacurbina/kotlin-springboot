package org.example.com.kotlinplayground.nulls

import kotlin.random.Random
import kotlin.random.nextInt

data class Movie(
	val id: Int?,
	val name: String
)

private fun saveMovie(movie: Movie): Movie {
	return movie.copy(id = Random.nextInt(0, 100))
}

private fun printName(name: String) {
	println("Name is: $name")
}

private fun printNameNullable(name: String?) {
	println("Name is: $name")
}

fun main() {
	var nameNullable: String? = null
	println("Value is: ${nameNullable?.length ?: 0}")
	nameNullable?.run {
		println(this)
	}

	nameNullable = "Alex"
	println("Value is: ${nameNullable.length}")
	printNameNullable(nameNullable)

	nameNullable = "Dilip"
	println("Value is: $nameNullable")

	var name: String = "Dilip"

	val movie = Movie(null, "Avengers")
	val savedMovie = saveMovie(movie)
	println(savedMovie.id!!)
	println("Saved movie: $savedMovie")
}
