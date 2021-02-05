package com.hardwaremartapi.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.tomcat.jni.File;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
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
		product.setShopkeeperId(p0.getShopkeeperId());
		product.setImageUrl(p0.getImageUrl());
		product.setSecondImageUrl(p0.getSecondImageUrl());
		product.setThirdImageurl(p0.getThirdImageurl());
		fireStore.collection("Product").document(product.getProductId()).set(product);
		return product;
	}

	public ArrayList<Product> viewProductOfShopkeeper(String name, String id)
			throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			name = name.toLowerCase();
			String doc = product.getName().toLowerCase();
			String doc2 = product.getShopkeeperId();
			if (doc.contains(name) && doc2.contains(id)) {
				pl.add(product);
			}
		}
		return pl;
	}

	public ArrayList<Product> getProductByCategoryAndShopKeeper(String categoryId, String shopkeeperId)
			throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<Product> pl = new ArrayList<Product>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Product").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			Product product = document.toObject(Product.class);
			if ((categoryId.equals(document.getString("categoryId")))
					&& (shopkeeperId.equals(document.getString("shopkeeperId")))) {
				pl.add(product);
			}
		}
		return pl;
	}

	public Product updateProductImages(ArrayList<MultipartFile> file, String productId)
			throws InterruptedException, ExecutionException, IOException {
		Firestore fireStore = FirestoreClient.getFirestore();
		Product product = fireStore.collection("Product").document(productId).get().get().toObject(Product.class);
		if (file.size() > 0) {
			String firstImageUrl = new FileUtility().uploadFile(file.get(0));
			product.setImageUrl(firstImageUrl);
			System.out.println("img1" + firstImageUrl);
			if (file.size() >= 2) {
				String secondImageUrl = new FileUtility().uploadFile(file.get(1));
				product.setSecondImageUrl(secondImageUrl);
				System.out.println("img2" + secondImageUrl);
				if (file.size() == 3) {
					String thirdImageUrl = new FileUtility().uploadFile(file.get(2));
					product.setThirdImageurl(thirdImageUrl);
					System.out.println("img3" + thirdImageUrl);
				}
			}
		}
		product.setTimestamp(System.currentTimeMillis());
		fireStore.collection("Product").document(productId).set(product);
		return product;
	}

	public Product updateProductImage(List<MultipartFile> files, String productId, int arr[])
			throws InterruptedException, ExecutionException, IOException {

		System.out.print("Oth" + arr[0] + " 1st" + arr[1] + "2nd" + arr[2]);
		System.out.println("Update Image method");
		System.out.println("Length : " + files.size());
		System.out.println("------");
		Firestore firestoredatabase = FirestoreClient.getFirestore();
		Product product = firestoredatabase.collection("Product").document(productId).get().get()
				.toObject(Product.class);

		if (files.size() == 1) {
			if (arr[0] == 1) {
				String firstImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setImageUrl(firstImageUrl);
				System.out.println("img1" + firstImageUrl);
			}

			if (arr[1] == 2) {
				String secondImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setSecondImageUrl(secondImageUrl);
				System.out.println("img2" + secondImageUrl);
			}

			if (arr[2] == 3) {
//				if (files.size() == 1 || files.size() == 2 || files.size() == 3) {
				String thirdImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setThirdImageurl(thirdImageUrl);
				System.out.println("img3" + thirdImageUrl);
			}
		}

		if (files.size() == 2) {
			if (arr[0] == 1 && arr[1] == 2) {
				String firstImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setImageUrl(firstImageUrl);
				System.out.println("img1" + firstImageUrl);

				String secondImageUrl = new FileUtility().uploadFile(files.get(1));
				product.setSecondImageUrl(secondImageUrl);
				System.out.println("img2" + secondImageUrl);
			}

			else if (arr[0] == 1 && arr[2] == 3) {
				String firstImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setImageUrl(firstImageUrl);
				System.out.println("img1" + firstImageUrl);

				String thirdImageUrl = new FileUtility().uploadFile(files.get(1));
				product.setThirdImageurl(thirdImageUrl);
				System.out.println("img3" + thirdImageUrl);
			}

			else if (arr[1] == 2 && arr[2] == 3) {
				String secondImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setSecondImageUrl(secondImageUrl);
				System.out.println("img2" + secondImageUrl);

				String thirdImageUrl = new FileUtility().uploadFile(files.get(1));
				product.setThirdImageurl(thirdImageUrl);
				System.out.println("img3" + thirdImageUrl);
			}
		}
		if (files.size() == 3) {
			if (arr[0] == 1) {
				String firstImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setImageUrl(firstImageUrl);
				System.out.println("img1" + firstImageUrl);
			}

			if (arr[1] == 2) {
				String secondImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setSecondImageUrl(secondImageUrl);
				System.out.println("img2" + secondImageUrl);
			}

			if (arr[2] == 3) {
				String thirdImageUrl = new FileUtility().uploadFile(files.get(0));
				product.setThirdImageurl(thirdImageUrl);
				System.out.println("img3" + thirdImageUrl);
			}
		}
		product.setTimestamp(System.currentTimeMillis());
		firestoredatabase.collection("Product").document(productId).set(product);
		return product;
	}

	public Product multProductImages(List<MultipartFile> files, Product product) throws IOException {
		System.out.println("Length : " + files.size());
		System.out.println("------");
		Firestore firestoredatabase = FirestoreClient.getFirestore();
		String productId = firestoredatabase.collection("Product").document().getId().toString();
		product.setProductId(productId);

		if (files.size() == 1) {
			String firstImageUrl = new FileUtility().uploadFile(files.get(0));
			product.setImageUrl(firstImageUrl);
			System.out.println("img1" + firstImageUrl);
		}
		if (files.size() == 2) {
			String firstImageUrl = new FileUtility().uploadFile(files.get(0));
			product.setImageUrl(firstImageUrl);
			System.out.println("img1" + firstImageUrl);
			String secondImageUrl = new FileUtility().uploadFile(files.get(1));
			product.setSecondImageUrl(secondImageUrl);
			System.out.println("img2" + secondImageUrl);
		}
		if (files.size() == 3) {
			String firstImageUrl = new FileUtility().uploadFile(files.get(0));
			product.setImageUrl(firstImageUrl);
			System.out.println("img1" + firstImageUrl);
			String secondImageUrl = new FileUtility().uploadFile(files.get(1));
			product.setSecondImageUrl(secondImageUrl);
			System.out.println("img2" + secondImageUrl);
			String thirdImageUrl = new FileUtility().uploadFile(files.get(2));
			product.setThirdImageurl(thirdImageUrl);
			System.out.println("img3" + thirdImageUrl);

		}
		product.setTimestamp(System.currentTimeMillis());
		firestoredatabase.collection("Product").document(productId).set(product);
		return product;

	}
}