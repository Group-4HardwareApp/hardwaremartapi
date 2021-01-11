package com.hardwaremartapi.controller;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.User;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("mobile") String mobile,
			@RequestParam("email") String email, @RequestParam("token") String token,
			@RequestParam("userId") String userId) throws Exception {

		if (file.isEmpty())
			throw new Exception();

		User user = new User();
		user.setName(name);
		user.setAddress(address);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setToken(token);
		user.setUserId(userId);
		User u = userService.saveUser(file, user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PostMapping("/updateuser")
	public ResponseEntity<User> updateShopkeeper(@RequestBody User user)
			throws InterruptedException, ExecutionException {
		User u = userService.updateUser(user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PostMapping("/updateuserimg")
	public ResponseEntity<User> updateUserImage(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") String userId) throws InterruptedException, ExecutionException, IOException {
		User u = userService.updateUserImage(file, userId);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId,
			@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("mobile") String mobile, @RequestParam("address") String address,
			@RequestParam("token") String token) throws IOException, InterruptedException, Exception {

		if (file.isEmpty())
			throw new Exception();
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setAddress(address);
		user.setUserId(userId);
		user.setToken(token);

		User u = userService.updateUser(file, user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PostMapping("/update/withoutImage")
	public ResponseEntity<?> updateUserWithoutImage(@RequestBody User user) throws Exception {

		User u = userService.updateUserWithoutImage(user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@GetMapping("/{currentUserId}")
	public ResponseEntity<User> getUserDetails(@PathVariable("currentUserId") String currentUserId) throws Exception {
		User user = userService.getUserDetails(currentUserId);
		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else

			throw new ResourceNotFoundException("User not found");
	}
}
