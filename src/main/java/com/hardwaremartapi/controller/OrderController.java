package com.hardwaremartapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.Order;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService ;
	
	@PostMapping("/placeOrder")  //save order
	public ResponseEntity<?> placeOrder(@RequestBody Order order){
        Order palcedOrder = orderService.placeOrders(order);
        return new ResponseEntity<Order>(palcedOrder,HttpStatus.OK);
	}
    
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("id")String id) throws Exception, Exception {
		Order order = orderService.deleteOrder(id);  
	     if(order != null)
	    	 return new ResponseEntity<Order>(order,HttpStatus.OK);
	     else
	    	 throw new ResourceNotFoundException("Order not found");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") String id) throws InterruptedException, ExecutionException, ResourceNotFoundException{
		Order order = orderService.getOrderById(id);
	    if(order!=null)
	    	return new ResponseEntity<Order>(order,HttpStatus.OK);
	    else
	    	throw new ResourceNotFoundException("Order not found");
	  }	
	
	@GetMapping("/status/{userId}")
	public ResponseEntity<List<Order>> getOrders(@PathVariable("userId") String userId) throws Exception {
		ArrayList<Order> order = orderService.getOrders(userId);
		if (order != null)
			return new ResponseEntity<List<Order>>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}
}
