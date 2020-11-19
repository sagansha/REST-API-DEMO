package com.irttaa.stu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.irttaa.stu.model.Student;

@Service
public class StuGatherServiceImpl implements StuGatherService {

	static List<Student> stulist = new ArrayList<>();
	private static final AtomicLong counter = new AtomicLong();

	static {
		stulist.add(new Student(counter.incrementAndGet(), "John", 23, "Group4"));
		stulist.add(new Student(counter.incrementAndGet(), "Jackson", 24, "Group2"));
		stulist.add(new Student(counter.incrementAndGet(), "John", 23, "Group1"));
	}

	public List<Student> getAllUsers() {
		return stulist;
	}

	public Student addNewUsers(Student student) {
		student.setId(counter.incrementAndGet());
		stulist.add(student);
		return student;
	}

	public void updateUser(Student student) {
		int index = stulist.indexOf(student);
		stulist.set(index, student);
	}

	public Student findById(long id) {
		for (Student student : stulist) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}

	public void deleteUser(long id) {
		for (Student stu : stulist) {
			if (stu.getId() == id) {
				stulist.remove(stu);
				break;
			}
		}
	}

	public boolean isStudentExist(Student student) {
		return findByName(student.getName()) != null;
	}

	private Student findByName(String stuName) {
		for (Student stu : stulist) {
			if (stu.getName().equals(stuName)) {
				return stu;
			}
		}
		return null;
	}

	@Override
	public void deleteAllUsers() {
		stulist.clear();
	}

}
