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

import com.google.gson.Gson;
import com.hardwaremartapi.bean.Product;
import com.hardwaremartapi.exception.ErrorDetails;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Product p = productService.deleteProduct(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Product> viewProduct(@PathVariable("id") String id)
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
	public ResponseEntity<Product> updateProduct(@RequestBody Product product)
			throws IOException, InterruptedException, ExecutionException {
		Product p = productService.updateProduct(product);
		return new ResponseEntity<Product>(p, HttpStatus.OK);

	}

	@GetMapping("/shopkeeperproducts/{name}/{shopkeeperId}")
	public ResponseEntity<List<Product>> viewProductOfShopkeeper(@PathVariable("name") String name,
			@PathVariable("shopkeeperId") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Product> productList = productService.viewProductOfShopkeeper(name, id);
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@GetMapping("/productlist/{categoryId}/{shopkeeperId}")
	public ResponseEntity<List<Product>> getProductByCategoryAndShopKeeper(
			@PathVariable("categoryId") String categoryId, @PathVariable("shopkeeperId") String shopkeeperId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		ArrayList<Product> productList = productService.getProductByCategoryAndShopKeeper(categoryId, shopkeeperId);
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@PostMapping("/uploadmultimages")
	public ResponseEntity<Product> multProductImages(@RequestParam("file") List<MultipartFile> file,
			@RequestParam("categoryId") String categoryId, @RequestParam("shopkeeperId") String shopkeeperId,
			@RequestParam("name") String name, @RequestParam("price") double price,
			@RequestParam("discount") double discount, @RequestParam("brand") String brand,
			@RequestParam("qtyInStock") int qtyInStock, @RequestParam("description") String description)
			throws Exception {
		Product product = new Product();
		product.setCategoryId(categoryId);
		product.setShopkeeperId(shopkeeperId);
		product.setName(name);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setBrand(brand);
		product.setDescription(description);
		product.setQtyInStock(qtyInStock);
		Product p = productService.multProductImages(file, product);
		return new ResponseEntity<Product>(p, HttpStatus.OK);

	}

	@PostMapping("/updateproductimg")
	public ResponseEntity<Product> updateProductImage(@RequestParam("file") List<MultipartFile> file,
			@RequestParam("productId") String productId, @RequestParam("arr") int arr[])
			throws InterruptedException, ExecutionException, IOException {
		System.out.println("***");
		Product p = productService.updateProductImage(file, productId, arr);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

}