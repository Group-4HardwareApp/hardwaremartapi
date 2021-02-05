package com.hardwaremartapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardwaremartapi.bean.BuyCart;
import com.hardwaremartapi.bean.Cart;
import com.hardwaremartapi.bean.Order;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;

	@PostMapping("/")
	public ResponseEntity<Cart> saveProductInCart(@RequestBody Cart cart) {
		Cart c = cartService.saveProductInCart(cart);
		return new ResponseEntity<Cart>(c, HttpStatus.OK);
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Cart> removeProductFormCart(@PathVariable("cartId") String cartId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Cart c = cartService.removeProductFromCart(cartId);
		if (c == null)
			throw new ResourceNotFoundException("Not Found");
		else
			return new ResponseEntity<>(c, HttpStatus.OK);
	}

	@GetMapping("/{currentUserId}")
	public ResponseEntity<ArrayList<Cart>> getCartProductList(@PathVariable("currentUserId") String currentUserId)
			throws InterruptedException, ExecutionException {
		ArrayList<Cart> list = cartService.getCartProductList(currentUserId);
		return new ResponseEntity<ArrayList<Cart>>(list, HttpStatus.OK);
	}

	@PostMapping("/buycart")
	public ResponseEntity<?> getProductQtyAddedInCart(@RequestBody BuyCart buyCart)
			throws InterruptedException, ExecutionException {
		BuyCart buycart = cartService.getProductWithQtyInStock(buyCart);
		return new ResponseEntity<>(buycart, HttpStatus.OK);
	}
}
