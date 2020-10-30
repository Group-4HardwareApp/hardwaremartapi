package com.hardwaremartapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwaremartapi.bean.Category;

@Service
public class CategoryService {
	
  public List<Category> getCategoryList() throws InterruptedException, ExecutionException{
	  List<Category> categoryList = new ArrayList<>();
	  Firestore fireStore = FirestoreClient.getFirestore();
      ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Category").get();
      List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
      for(QueryDocumentSnapshot documentSnapShot : list) {
    	  Category c = documentSnapShot.toObject(Category.class);
          categoryList.add(c);
      }
      return categoryList;
  }
  
  public Category getCategoryById(String categoryId) throws InterruptedException, ExecutionException {
	  Firestore fireStore = FirestoreClient.getFirestore();
	  Category category = fireStore.collection("Category").document(categoryId).get().get().toObject(Category.class);
      return category;
  }
}
