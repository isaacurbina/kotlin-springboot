package com.kotlinplayground;

import org.example.com.kotlinplayground.classes.Course;
import org.example.com.kotlinplayground.classes.CourseCategory;

public class InvokeKotlinFromJava {

	public static void main(String[] args) {
		final Course course = new Course(
			1,
			"Reactive Programming in Modern Java using Project Reactor",
			"Dilip",
			CourseCategory.DEVELOPMENT
		);
		System.out.println("course: " + course);
	}
}
