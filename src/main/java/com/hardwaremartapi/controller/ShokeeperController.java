package com.hardwaremartapi.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import com.hardwaremartapi.bean.Product;
import com.hardwaremartapi.bean.Shopkeeper;
import com.hardwaremartapi.bean.User;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.ShopkeeperService;

@RestController
@RequestMapping("/shopkeeper")
public class ShokeeperController {

	@Autowired
	ShopkeeperService shopkeeperService;

	@PostMapping("/")
	public ResponseEntity<Shopkeeper> saveShopkeeper(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("shopName") String shopName,
		    @RequestParam("contactNumber") String contactNumber,@RequestParam("shopkeeperId")String shopkeeperId,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("token") String token) throws Exception {
		Shopkeeper shopkeeper = new Shopkeeper();
		shopkeeper.setName(name);
		shopkeeper.setShopName(shopName);
		shopkeeper.setShopkeeperId(shopkeeperId);
		shopkeeper.setAddress(address);
		shopkeeper.setEmail(email);
		shopkeeper.setContactNumber(contactNumber);
		shopkeeper.setToken(token);
		Shopkeeper s = shopkeeperService.saveShopkeeper(file, shopkeeper);
		return new ResponseEntity<Shopkeeper>(s, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> viewShopkeeper(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Shopkeeper s = shopkeeperService.viewShopkeeper(id);
		if (s != null) {
			return new ResponseEntity<Shopkeeper>(s, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Shopkeeper Not Found");
		}
	}

	@PostMapping("/updateshopkeeper")
	public ResponseEntity<Shopkeeper> updateShopkeeper(@RequestBody Shopkeeper shopkeeper)
			throws InterruptedException, ExecutionException {
		Shopkeeper s = shopkeeperService.updateShopkeeper(shopkeeper);
		return new ResponseEntity<Shopkeeper>(s, HttpStatus.OK);
	}

	@PostMapping("/updateshopkeeperimg")
	public ResponseEntity<Shopkeeper> updateShopkeeperImage(@RequestParam("file") MultipartFile file,
			@RequestParam("shopkeeperId") String shopkeeperId)
			throws InterruptedException, ExecutionException, IOException {
		Shopkeeper s = shopkeeperService.updateShopkeeperImage(file, shopkeeperId);
		return new ResponseEntity<Shopkeeper>(s, HttpStatus.OK);
	}
	
	@PostMapping("/updatetoken/{shopkeeperId}/{token}")
	public ResponseEntity<Shopkeeper> updateShopkeeperToken(@PathVariable("shopkeeperId") String shopkeeperId,@PathVariable("token") String token)
			throws InterruptedException, ExecutionException {
		Shopkeeper s = shopkeeperService.updateShopkeeperToken(shopkeeperId,token);
		return new ResponseEntity<Shopkeeper>(s, HttpStatus.OK);
	}
}
