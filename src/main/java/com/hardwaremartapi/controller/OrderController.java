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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.Order;
import com.hardwaremartapi.bean.OrderCart;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.OrderService;
import com.hardwaremartapi.bean.PurchaseOrder;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/placeOrder") // save order
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order palcedOrder = orderService.placeOrders(order);
		return new ResponseEntity<Order>(palcedOrder, HttpStatus.OK);
	}

	@PostMapping("/placeCartOrder") // save order
	public ResponseEntity<OrderCart> placeCartOrder(@RequestBody OrderCart order) {
		OrderCart palcedOrder = orderService.placeCartOrders(order);
		return new ResponseEntity<OrderCart>(palcedOrder, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") String orderId) throws Exception, Exception {
		Order order = orderService.deleteOrder(orderId);
		if (order != null)
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}

	@PostMapping("/cancel/{orderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("orderId") String orderId) throws ResourceNotFoundException, Exception, ExecutionException {
		Order order = orderService.cancelOrder(orderId);
		if (order != null)
			return new ResponseEntity<Order>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Order order = orderService.getOrderById(id);
		if (order != null)
			return new ResponseEntity<Order>(order, HttpStatus.OK);
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

	@GetMapping("/active/{userId}")
	public ResponseEntity<List<Order>> getActiveOrders(@PathVariable("userId") String userId) throws Exception {
		ArrayList<Order> orderList = orderService.getActiveOrders(userId);
		if (orderList != null)
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Orders not found");
	}

	@GetMapping("/cancel/{userId}")
	public ResponseEntity<List<Order>> getCancelledOrders(@PathVariable("userId") String userId) throws Exception {
		ArrayList<Order> orderList = orderService.getCancelledOrders(userId);
		if (orderList != null)
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Orders not found");
	}

	@GetMapping("/deliver/{userId}")
	public ResponseEntity<List<Order>> getDeliveredOrders(@PathVariable("userId") String userId) throws Exception {
		ArrayList<Order> orderList = orderService.getDeliveredOrders(userId);
		if (orderList != null)
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Orders not found");
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrderOfCurrentUser(@PathVariable("userId") String userId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Order> order = orderService.getOrderOfCurrentUser(userId);
		if (order != null)
			return new ResponseEntity<List<Order>>(order, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order not found");

	}

	@GetMapping("/orderHistory/{shopKeeperId}")
	public ResponseEntity<ArrayList<PurchaseOrder>> getPurchaseOrder(@PathVariable("shopKeeperId") String shopKeeperId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<PurchaseOrder> orderList = orderService.getPurchaseOrder(shopKeeperId);
		if (orderList != null)
			return new ResponseEntity<ArrayList<PurchaseOrder>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order Not Found");
	}

	@GetMapping("/onwayorders/{shopKeeperId}")
	public ResponseEntity<ArrayList<PurchaseOrder>> getOnGoingOrder(@PathVariable("shopKeeperId") String shopKeeperId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<PurchaseOrder> orderList = orderService.getOnGoingOrder(shopKeeperId);
		if (orderList != null)
			return new ResponseEntity<ArrayList<PurchaseOrder>>(orderList, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Order Not Found");
	}
}
