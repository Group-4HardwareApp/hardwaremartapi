package com.hardwaremartapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hardwaremartapi.bean.Product;
import com.hardwaremartapi.exception.ErrorDetails;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/")
	public ResponseEntity<?> saveProduct(@RequestParam("file") MultipartFile file,
			@RequestParam("categoryId") String categoryId, @RequestParam("shopKeeperId") String shopKeeperId,
			@RequestParam("name") String name, @RequestParam("price") double price,
			@RequestParam("discount") double discount, @RequestParam("brand") String brand,
			@RequestParam("qtyInStock") int qtyInStock, @RequestParam("description") String description)
			throws ResourceNotFoundException, IOException {

		if (file.isEmpty()) {
			throw new ResourceNotFoundException("File not found");
		}

		Product product = new Product();
		product.setCategoryId(categoryId);
		product.setshopKeeperId(shopKeeperId);
		product.setName(name);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setBrand(brand);
		product.setDescription(description);
		product.setQtyInStock(qtyInStock);
		Product p = productService.saveProduct(file, product);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Product p = productService.deleteProduct(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> viewProduct(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Product p = productService.viewProduct(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/discount")
	public ResponseEntity<List<Product>> getProductByDiscount()
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Product> productList = productService.getProductByDiscount();
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> searchProductByName(@PathVariable("name") String name)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Product> productList = productService.searchProductByName(name);
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/productlist/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("categoryId") String categoryId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Product> productList = productService.getProductByCategory(categoryId);
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/recent")
	public ResponseEntity<List<Product>> getRecentProducts() throws InterruptedException, ExecutionException {
		ArrayList<Product> productList = productService.getRecentProducts();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@PostMapping("/updateproduct")
	public ResponseEntity<Product> updateProduct(
			@RequestParam("categoryId") String categoryId, 
			@RequestParam("productId") String productId,
			@RequestParam("name") String name,
			@RequestParam("price") double price, 
			@RequestParam("discount") double discount,
			@RequestParam("brand") String brand,
			@RequestParam("qtyInStock") int qtyInStock,
			@RequestParam("description") String description) throws IOException, InterruptedException, ExecutionException 
	{
		
		Product product = new Product();
		product.setProductId(productId);
		product.setCategoryId(categoryId);
		product.setName(name);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setBrand(brand);
		product.setDescription(description);
		product.setQtyInStock(qtyInStock);
		Product p = productService.updateProduct(product);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
		
	}
	
	@PostMapping("/updateproductimg")	
	public ResponseEntity<Product> updateProductImage(@RequestParam("file") MultipartFile file,
			@RequestParam("productId") String productId) throws InterruptedException, ExecutionException, IOException
	{
		Product p = productService.updateProductImage(file,productId);	
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	
	
	
	
	
	
	


}
