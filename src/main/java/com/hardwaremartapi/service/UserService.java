package com.hardwaremartapi.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.firestore.Firestore;

import com.google.firebase.cloud.FirestoreClient;
import com.hardwaremartapi.FileUtility;
import com.hardwaremartapi.bean.User;

@Service
public class UserService {

	FileUtility fileUtility = new FileUtility();

	public User updateUser(MultipartFile file, User user) throws IOException, InterruptedException, Exception {
		Firestore firestoredatabase = FirestoreClient.getFirestore();
		String imageUrl = fileUtility.uploadFile(file);
		
		user.setImageUrl(imageUrl);;
		user.setUserId(user.getUserId());
		firestoredatabase.collection("User").document(user.getUserId()).set(user);
		return user;
	}
    
	public User saveUser(MultipartFile file, User user) throws Exception {
		Firestore firestoredatabase = FirestoreClient.getFirestore();
		String imageUrl = fileUtility.uploadFile(file);
		user.setImageUrl(imageUrl);
		String userId = firestoredatabase.collection("User").document().getId().toString();
		user.setUserId(userId);
		firestoredatabase.collection("User").document(userId).set(user);
		return user;
	}
	
	
	
	
	
	
	
}