package org.example.com.kotlinplayground.classes

class Person(
	val name: String = "",
	val age: Int = 0
) {
	var email: String = ""

	constructor(
		_email: String,
		_name: String = "",
		_age: Int = 0
	) : this(_name, _age) {
		this.email = _email
	}

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

	val person2 = Person(_email = "abc@gmail.com", _age = 31)
	println("Name: ${person2.name} and Age: ${person2.age} and Email: ${person2.email}")
}
