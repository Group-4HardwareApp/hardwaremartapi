package com.hardwaremartapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardwaremartapi.bean.Product;
import com.hardwaremartapi.bean.ProductName;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.ProductNameService;

@RestController
@RequestMapping("/productname")
public class ProductNameController {

	@Autowired
	ProductNameService productNameService;

	@PostMapping("/")
	public ResponseEntity<ProductName> addProductName(@RequestBody ProductName productName) {
		ProductName pn = productNameService.addProductName(productName);
		return new ResponseEntity<ProductName>(pn, HttpStatus.OK);
	}

	@GetMapping("/{productNameId}")
	public ResponseEntity<ProductName> getProductName(@PathVariable("productNameId") String productNameId)
			throws ResourceNotFoundException, InterruptedException, ExecutionException {
		ProductName pn = productNameService.getProductName(productNameId);
		if (pn == null)
			throw new ResourceNotFoundException("Product Info not found");
		else
			return new ResponseEntity<ProductName>(pn, HttpStatus.OK);
	}

	@GetMapping("/productnamelist/{categoryId}")
	public ResponseEntity<List<ProductName>> getProductNameListByCategory(@PathVariable("categoryId") String categoryId)
			throws ResourceNotFoundException, InterruptedException, ExecutionException {

		ArrayList<ProductName> productNameList = productNameService.getProductNameListByCategory(categoryId);

		if (productNameList == null)
			throw new ResourceNotFoundException("Product Info not found");
		else
			return new ResponseEntity<List<ProductName>>(productNameList, HttpStatus.OK);
	}
	/*
	 * //working on this
	 * 
	 * @PostMapping("/addmultipleproductname") public
	 * ResponseEntity<List<ProductName>> addMultipleProductName(@RequestBody
	 * ProductName productName) { ArrayList<ProductName>
	 * productNamList=productNameService.addMultipleProductName(); ProductName pn =
	 * productNameService.addProductName(productName); return new
	 * ResponseEntity<List<ProductName>(HttpStatus.OK); }
	 * 
	 */
}