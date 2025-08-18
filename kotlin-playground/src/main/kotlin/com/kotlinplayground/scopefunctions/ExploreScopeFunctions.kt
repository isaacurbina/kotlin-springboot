package org.example.com.kotlinplayground.scopefunctions

import org.example.com.kotlinplayground.classes.Course
import org.example.com.kotlinplayground.classes.CourseCategory

fun main() {
    exploreApply()
    exploreAlso()
    exploreLet()
}

private fun exploreApply() {
    val course = Course(
        id = 1,
        name = "Design Thinking in Kotlin",
        author = "Dilip"
    ).apply {
        this.courseCategory = CourseCategory.DESIGN
    }

    println("course: $course")
}

private fun exploreAlso() {
    Course(
        id = 1,
        name = "Design Thinking in Kotlin",
        author = "Dilip"
    ).also {
        println("Course is $it")
    }
}

private fun exploreLet() {
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    val result = numbers.map { it * 2 }.filter { it > 5 }.let {
        println(it)
        it.sum()
    }
    println(result)

    var name: String? = null
    name = "Dilip"
    val result1 = name?.let {
        println(it)
        it.uppercase()
    }
    println(result1)
}
