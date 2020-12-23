package com.hardwaremartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.OrderItems;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.OrderItemService;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
	@Autowired
    OrderItemService orderItemService; 
	
	@PostMapping("/")
	public ResponseEntity<?> getItems(@RequestParam("file") MultipartFile file, 
			@RequestParam("quantity") int quantity,
			@RequestParam("productName") String productName,
			@RequestParam("shopkeeperId") String shopkeeperId,
			@RequestParam("amount") double amount,
			@RequestParam("orderId") String orderId ) throws Exception  {
		    
		   if(file.isEmpty())
			   throw new ResourceNotFoundException("File not found");
		
		   OrderItems items = new OrderItems();
		   items.setName(productName);
           items.setQty(quantity);
           items.setShopkeeperId(shopkeeperId);
           
           OrderItems item = orderItemService.getItems(file, items, orderId);
	       return new ResponseEntity<OrderItems>(item, HttpStatus.OK);
	}
}
