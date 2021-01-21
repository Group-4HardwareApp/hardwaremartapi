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
import com.google.firestore.admin.v1.Index.QueryScope;
import com.hardwaremartapi.bean.Cart;
import com.hardwaremartapi.bean.ProductName;

@Service
public class ProductNameService {

	public ProductName addProductName(ProductName productName) {
		Firestore fireStore = FirestoreClient.getFirestore();
		String productNameId = fireStore.collection("ProductName").document().getId();
		productName.setProductNameId(productNameId);
		fireStore.collection("ProductName").document(productNameId).set(productName);
		return productName;
	}

	public ProductName getProductName(String productNameId) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ProductName productName = fireStore.collection("ProductName").document(productNameId).get().get()
				.toObject(ProductName.class);
		return productName;
	}

	public ArrayList<ProductName> getProductNameListByCategory(String categoryId)
			throws InterruptedException, ExecutionException {

		Firestore fireStore = FirestoreClient.getFirestore();
		ArrayList<ProductName> pnl = new ArrayList<>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("ProductName").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			ProductName productName = document.toObject(ProductName.class);
			String catId = productName.getCategoryId();
			if (catId.equals(categoryId)) {
				pnl.add(productName);
			}
		}
		return pnl;
	}
}