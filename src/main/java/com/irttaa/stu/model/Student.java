package com.irttaa.stu.model;

public class Student {

	private long id;
	private String name;
	private int age;
	private String subject;

	public Student(long id, String name, int age, String subject) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.subject = subject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
