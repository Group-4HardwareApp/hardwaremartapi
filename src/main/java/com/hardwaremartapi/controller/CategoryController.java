package com.hardwaremartapi.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardwaremartapi.bean.Category;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired	
  CategoryService categoryService;
  
  @GetMapping("/")	
  public ResponseEntity<?> getCategoryList() throws InterruptedException, ExecutionException{
	List<Category>categoryList = categoryService.getCategoryList();  
    return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
  }
  
  @GetMapping("/{categoryId}")
  public ResponseEntity<?> getCategoryById(@PathVariable("categoryId") String categoryId) throws InterruptedException, ExecutionException, ResourceNotFoundException{
     Category c = categoryService.getCategoryById(categoryId);	  
     if(c == null)
    	 throw new ResourceNotFoundException("Category not found");
     else
    	 return new ResponseEntity<Category>(c,HttpStatus.OK);
  }
}
