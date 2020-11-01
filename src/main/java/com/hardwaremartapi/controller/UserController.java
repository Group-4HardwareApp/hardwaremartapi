package com.hardwaremartapi.controller;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.User;
import com.hardwaremartapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("address") String address, 
			@RequestParam("mobile") String mobile,
			@RequestParam("email") String email,
			@RequestParam("token") String token) throws Exception {

		if (file.isEmpty())
			throw new Exception();
		
		User user = new User();
		user.setName(name);
		user.setAddress(address);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setToken(token);
		
		User u = userService.saveUser(file, user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestParam("file") MultipartFile file, 
			@RequestParam("userId") String userId,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("mobile") String mobile, 
			@RequestParam("email") String email,
			@RequestParam("token") String token) throws Exception {
		if (file.isEmpty())
			throw new Exception();
		User user = new User();
		user.setAddress(address);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setName(name);
		user.setToken(token);
		user.setUserId(userId);
		User u = userService.updateUser(file, user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	
     	
}

