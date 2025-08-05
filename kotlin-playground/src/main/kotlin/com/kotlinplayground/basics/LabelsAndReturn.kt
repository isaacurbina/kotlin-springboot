package org.example.com.kotlinplayground.basics

fun main() {
	for (i in 1..5) {
		println("i: $i")
		if (i == 3) break
	}

	label()

	for (i in 1..5) {
		println("i in return: $i")
		if (i == 3) return
	}

	println("End of the Program!")
}

private fun label() {
	loop@ for (i in 1..5) {
		println("i in label: $i")
		//if (i == 3) break@loop

		innerloop@ for(j in 1..5) {
			println("j in label: $j")
			//if (j == 2) break@innerloop
			if (j == 2) continue@loop
		}
	}
}
