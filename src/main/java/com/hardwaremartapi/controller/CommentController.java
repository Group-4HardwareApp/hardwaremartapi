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

import com.hardwaremartapi.bean.Comment;
import com.hardwaremartapi.bean.Order;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService ratingService;
	
	@PostMapping("/post")
	public ResponseEntity<Comment> commentProduct(@RequestBody Comment comment){
		Comment rating = ratingService.commentProduct(comment);
		return new ResponseEntity<Comment>(rating, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{productId}/{commentId}")
	public ResponseEntity<Comment> deleteComment(@PathVariable("productId")String productId,
			@PathVariable("commentId") String commentId) throws Exception{
		Comment rating = ratingService.deleteComment(productId,commentId);
		if(rating != null)
	    	return new ResponseEntity<Comment>(rating, HttpStatus.OK);
		else 
			throw new ResourceNotFoundException("Comment not found");
	}
	
	@GetMapping("/getComment/{productId}")
	public ResponseEntity<ArrayList<Comment>> getCommentOfProduct(@PathVariable("productId") String productId) throws Exception{
		ArrayList<Comment> commentList = ratingService.getCommentOfProduct(productId);
		if (commentList != null)
			return new ResponseEntity<ArrayList<Comment>>(commentList, HttpStatus.OK
					);
		else
			throw new ResourceNotFoundException("Comments not found");
	}
	
	@PostMapping("/update/{commentId}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("commentId")String commentId) throws Exception, ExecutionException{
		Comment com = ratingService.updateComment(comment,commentId);
		return new ResponseEntity<Comment>(com, HttpStatus.OK);
	}	
}
