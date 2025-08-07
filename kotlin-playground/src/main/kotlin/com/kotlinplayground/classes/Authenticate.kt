package org.example.com.kotlinplayground.classes

object Authenticate {
	fun authenticate(username: String, password: String) {
		println("User authenticate for userName: $username")
	}
}

fun main() {
	Authenticate.authenticate("abc", "1234")
}
