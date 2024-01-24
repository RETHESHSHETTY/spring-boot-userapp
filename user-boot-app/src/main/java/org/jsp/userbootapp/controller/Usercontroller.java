package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Usercontroller {
	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		
		return service.saveUser(user);
	}

	@PutMapping("/users")
	public ResponseStructure<User> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@GetMapping("/users/{id}")
	public ResponseStructure <User> findById(@PathVariable(name = "id") int id) {
		return service.findById(id);
		
	}

	@DeleteMapping("/users/{id}")
	public  ResponseStructure <Boolean>   deleteById(@PathVariable(name = "id") int id) {
		return service.deleteById(id);
	}

	@GetMapping("/users")
	public ResponseStructure <List<User>> findAll() {
		return service.findAll();
	}

	@PostMapping("/users/verify-by-phone")
	public  ResponseStructure <User> verifyUser(@RequestParam long phone, @RequestParam String password) {
		return service.verifyUser(phone,password);
		
	}

}
