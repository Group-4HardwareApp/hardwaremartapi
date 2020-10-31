package com.hardwaremartapi.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwaremartapi.FileUtility;
import com.hardwaremartapi.bean.Product;

@Service
public class ProductService {

	public Product saveProduct(MultipartFile file, Product product) throws IOException {
		FileUtility fileUtility = new FileUtility();
		String imageUrl = fileUtility.uploadFile(file);
		product.setImageUrl(imageUrl);
		Firestore fireStore = FirestoreClient.getFirestore();
		product.setTimestamp(System.currentTimeMillis());
		String productId = fireStore.collection("Product").document().getId().toString();
		product.setProductId(productId);
		fireStore.collection("Product").document(productId).set(product);
		return product;
	}

	public Product deleteProduct(String id) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		Product product = fireStore.collection("Product").document(id).get().get().toObject(Product.class);
		if (product != null) {
			fireStore.collection("Product").document(id).delete();
		}
		return product;
	}

	public Product viewProduct(String id) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		Product product = fireStore.collection("Product").document(id).get().get().toObject(Product.class);
		return product;
	}

	public ArrayList<Product> searchProductByName(String name) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			name = name.toLowerCase();
			String doc = product.getName().toLowerCase();
			if (doc.contains(name)) {
				pl.add(product);
			}
		}
		return pl;
	}

	public ArrayList<Product> getProductByCategory(String id) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			if (id.equals(document.getString("categoryId"))) {
				pl.add(product);
			}
		}
		return pl;
	}

	public ArrayList<Product> getProductByDiscount() throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").orderBy("discount", Direction.DESCENDING)
				.limit(10).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			double discount = product.getDiscount();
			if (discount > 0) {
				pl.add(product);
			}
		}
		return pl;
	}

	public ArrayList<Product> getRecentProducts() throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").orderBy("timestamp", Direction.DESCENDING)
				.limit(10).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			pl.add(product);
		}
		return pl;
	}

	public Product updateProduct(Product product) throws IOException, InterruptedException, ExecutionException {

		Firestore fireStore = FirestoreClient.getFirestore();
		Product p0 = fireStore.collection("Product").document(product.getProductId()).get().get()
				.toObject(Product.class);
		product.setTimestamp(System.currentTimeMillis());
		product.setShopKeeperId(p0.getShopKeeperId());
		product.setImageUrl(p0.getImageUrl());
		fireStore.collection("Product").document(product.getProductId()).set(product);
		return product;
	}

	public Product updateProductImage(MultipartFile file, String productId)
			throws InterruptedException, ExecutionException, IOException {
		Firestore fireStore = FirestoreClient.getFirestore();
		Product product = fireStore.collection("Product").document(productId).get().get().toObject(Product.class);
		FileUtility fileUtility = new FileUtility();
		String imageUrl = fileUtility.uploadFile(file);
		product.setImageUrl(imageUrl);
		fireStore.collection("Product").document(productId).set(product);
		return product;
	}
}
