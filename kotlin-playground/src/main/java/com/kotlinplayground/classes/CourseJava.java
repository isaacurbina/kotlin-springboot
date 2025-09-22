package com.kotlinplayground.classes;

public class CourseJava {

	private Integer id;
	private String name;
	private String author;

	public CourseJava(Integer id, String name, String author) {
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public String toString() {
		return "Course(id=" + id + ", name=" + name + ", author=" + author + ")";
	}
}
