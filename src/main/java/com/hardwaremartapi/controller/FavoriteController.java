package com.hardwaremartapi.controller;

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
import com.hardwaremartapi.bean.Favorite;
import com.hardwaremartapi.exception.ResourceNotFoundException;
import com.hardwaremartapi.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
	@Autowired
	FavoriteService favoriteService;
	private Favorite favorite;

	@PostMapping("/")
	public Favorite addFavorite(@RequestBody Favorite favorite) throws Exception {

		return favoriteService.addFavorite(favorite);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeFavorite(@PathVariable("id") String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Favorite favorite = favoriteService.removeFavorite(id);
		if (favorite != null)
			return new ResponseEntity<Favorite>(favorite, HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Not found" + id);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<Favorite>> getFavourite(@PathVariable String userId)
			throws ResourceNotFoundException, InterruptedException, ExecutionException {
		List<Favorite> favouriteList = favoriteService.getFavorite(userId);
		if (favouriteList != null) {
			return new ResponseEntity<List<Favorite>>(favouriteList, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}
}
