package org.example.com.kotlinplayground.classes

class Item() {
	var name: String = ""

	constructor(_name: String) : this() {
		this.name = _name
	}
}

fun main() {
	val item = Item("Iphone")
	println("Item name is ${item.name}")
	item.name = "Samsung"
	println("Item name is ${item.name}")
	println(item)
}
