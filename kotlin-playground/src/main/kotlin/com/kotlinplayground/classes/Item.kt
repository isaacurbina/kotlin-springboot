package org.example.com.kotlinplayground.classes

class Item() {
	var name: String = ""
	var price: Double = 0.0
		get() {
			println("Inside Getter")
			return field
		}
		set(value) {
			println("Inside Setter")
			if (value >= 0.0) {
				field = value

			} else {
				throw IllegalArgumentException("Negative Price is not Allowed")
			}
		}

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
	println(item.price)
	item.price = 12.99
	println(item.price)
}
