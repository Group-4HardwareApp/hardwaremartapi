package com.hardwaremartapi.service;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import com.hardwaremartapi.FileUtility;
import com.hardwaremartapi.bean.Order;
import com.hardwaremartapi.bean.OrderItems;

@Service
public class OrderService {

	Firestore fireStore = FirestoreClient.getFirestore();
	OrderItems orderItem = new OrderItems();
	
	public Order placeOrders(Order order) {
		String orderId = fireStore.collection("Order").document().getId().toString();
		order.setOrderId(orderId);
		fireStore.collection("Order").document(order.getOrderId()).set(order);
		return order;
	}
	
	public Order getOrderById(String id) throws InterruptedException, ExecutionException  {
        Order order = fireStore.collection("Order").document(id).get().get().toObject(Order.class);
		return order;
	}
	
	public Order deleteOrder(String id) throws InterruptedException, ExecutionException {
		Order order = fireStore.collection("Order").document(id).get().get().toObject(Order.class);
		if(order != null) {
    		fireStore.collection("Order").document(id).delete();  
		}    
		return order;
	}	
	
	public ArrayList<Order> getOrders(String currentUserId) throws Exception, Exception {
		ArrayList<Order> list = new ArrayList<Order>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Order").whereIn("shippingStatus",Arrays.asList("Delivered","Cancelled")).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documentSnapshotList) {
			Order order = document.toObject(Order.class);
			list.add(order);
		}
		return list;
	}
	public ArrayList<Order> getOrderOfCurrentUser(String currentUserId) throws InterruptedException, ExecutionException{
		ArrayList<Order> list = new ArrayList<>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Order").whereEqualTo("userId", currentUserId).get();
		QuerySnapshot snapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotsList = snapshot.getDocuments();
		for(QueryDocumentSnapshot document : documentSnapshotsList) {
			Order order = document.toObject(Order.class);
			list.add(order);
		}
		return list;
	}
}
