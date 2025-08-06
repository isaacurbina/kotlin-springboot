package org.example.com.kotlinplayground.classes

class Person(
	val name: String = "",
	val age: Int = 0
) {
	fun action() {
		println("Person Walks")
	}
}

fun main() {
	val person = Person("Alex", 35)
	person.action()
	println("Name: ${person.name} and Age: ${person.age}")

	val person1 = Person()
	println("Name: ${person1.name} and Age: ${person1.age}")
}
