package com.irttaa.stu.service;

import java.util.List;

import com.irttaa.stu.model.Student;

public interface StuGatherService {

	List<Student> getAllUsers();

	boolean isStudentExist(Student student);

	Student addNewUsers(Student student);

	Student findById(long id);

	void updateUser(Student currStudent);

	void deleteUser(long id);

	void deleteAllUsers();

}
