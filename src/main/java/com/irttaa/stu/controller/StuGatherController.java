package com.irttaa.stu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.irttaa.stu.model.Student;
import com.irttaa.stu.service.StuGatherService;
import com.irttaa.stu.util.StuGatherError;

@RestController
@RequestMapping("/v1")
public class StuGatherController {

	@Autowired
	StuGatherService stuGatherService;

	public static final Logger logger = LoggerFactory.getLogger(StuGatherController.class);

//	@RequestMapping("/hello")
//	public String getHello() {
//		return "hello world";
//	}

	/*----------------------- Get all users -------------------*/
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers() {
		logger.info("Fetching all users...");
		List<Student> student = stuGatherService.getAllUsers();
		if (student.isEmpty()) {
			logger.info("No user exist!");
			return new ResponseEntity<>(new StuGatherError("No user exist"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(student, HttpStatus.OK);

	}

	/*----------------------- Add new user -------------------*/
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> addNewUsers(@RequestBody Student student) {
		logger.info("Adding a new user {}", student.getName());
		if (stuGatherService.isStudentExist(student)) {
			logger.error("Unable to create. A User with name {} already exist", student.getName());
			return new ResponseEntity<>(
					new StuGatherError("Unable to create. A User with name" + student.getName() + " already exist"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(stuGatherService.addNewUsers(student), HttpStatus.OK);
	}
	
	/*----------------------- Update user -------------------*/

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Student student) {
		logger.info("Fetching & Updating the User with id {}", id);
		Student currStudent = stuGatherService.findById(id);
		if (currStudent == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity<>(new StuGatherError("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currStudent.setAge(student.getAge());
		currStudent.setName(student.getName());
		currStudent.setSubject(student.getSubject());
		stuGatherService.updateUser(currStudent);
		return new ResponseEntity<>(currStudent, HttpStatus.OK);
	}
	
	/*----------------------- Delete single user -------------------*/

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUsers(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);
		Student currStudent = stuGatherService.findById(id);
		if (currStudent == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<>(new StuGatherError("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		stuGatherService.deleteUser(id);
		return new ResponseEntity<>(currStudent, HttpStatus.NO_CONTENT);
	}
	
	/*----------------------- Get single users -------------------*/

	@RequestMapping(value = "/stu/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getuser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Student student = stuGatherService.findById(id);
		if (student == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<>(new StuGatherError("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	/*----------------------- Delete all users -------------------*/

	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUsers() {
		logger.info("Deleting all users...");
		List<Student> currStudents = stuGatherService.getAllUsers();
		if (currStudents.isEmpty()) {
			logger.error("Unable to delete. No user not found.");
			return new ResponseEntity<>(new StuGatherError("Unable to delete. No user found."), HttpStatus.NOT_FOUND);
		}
		stuGatherService.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
