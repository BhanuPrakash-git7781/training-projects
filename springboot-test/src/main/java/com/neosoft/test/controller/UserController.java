package com.neosoft.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.test.bean.User;
import com.neosoft.test.service.UserService;


@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	// http://localhost:8080/users
	@GetMapping("/users")
	// OR @RequestMapping(method=RequestMethod.GET, value="/users")
	public List<User> getUsers() {
		logger.info("/users called :: "+new java.util.Date().toString());		
		return userService.getAllUsers();
	}
	// #Set to only warnings (info will be not shown on console)  logging.level.root=WARN

	// http://localhost:8080/user/102
	@GetMapping("/user/{id}")	
	public User getUser(@PathVariable int id) {
		return userService.getUserById(id);
	}

	// http://localhost:8080/users
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	// http://localhost:8080/user/102
	@PutMapping("/user/{id}")
	public void updateUser(@RequestBody User user, @PathVariable int id) {
		userService.updateUser(user, id);
	}

	// http://localhost:8080/user/102
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
	//http://localhost:8080/user/sortByUname
	@GetMapping("/users/sortByUname")
	public List<User> getSortByNameUsers(){
		return userService.sortByUname();
	}
	
}
