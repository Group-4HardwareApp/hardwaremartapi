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
import com.hardwaremartapi.bean.BuyCart;
import com.hardwaremartapi.bean.Cart;
import com.hardwaremartapi.bean.Product;

@Service
public class CartService {
  
  public Cart saveProductInCart(Cart cart) {
	  Firestore fireStore = FirestoreClient.getFirestore();
	  String cartId = fireStore.collection("Cart").document().getId();
	  cart.setCartId(cartId);
	  fireStore.collection("Cart").document(cartId).set(cart);
	  return cart;
  }
  
  public Cart removeProductFromCart(String cartId) throws InterruptedException, ExecutionException {
	  Firestore fireStore = FirestoreClient.getFirestore();
	  Cart cart = fireStore.collection("Cart").document(cartId).get().get().toObject(Cart.class);
	  if(cart != null)
		  fireStore.collection("Cart").document(cartId).delete();
      return cart;
  }

  public ArrayList<Cart> getCartProductList(String currentUserId) throws InterruptedException, ExecutionException{
	  ArrayList<Cart> cartList = new ArrayList<>();
	  Firestore fireStore = FirestoreClient.getFirestore();
	  ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("Cart").whereEqualTo("userId", currentUserId).get();
	  QuerySnapshot snapshot = apiFuture.get();
	  List<QueryDocumentSnapshot> documentSnapshot = snapshot.getDocuments();
	  for(QueryDocumentSnapshot document :documentSnapshot) {
		  Cart c = document.toObject(Cart.class);
	      cartList.add(c);
	  }
	  return cartList;
  }
  public BuyCart getProductWithQtyInStock(BuyCart buyCart) throws InterruptedException, ExecutionException{
	  Firestore fireStore = FirestoreClient.getFirestore();
	  ArrayList<Cart> cartList = buyCart.getCartList();
	  ArrayList<Cart>al = new ArrayList<>();
	  for(Cart c : cartList) {
		  String productId = c.getProductId();
		  Product p = fireStore.collection("Product").document(productId).get().get().toObject(Product.class);
	      c.setQtyInStock(p.getQtyInStock());
	      al.add(c);
	  }
	  buyCart.setCartList(al);
	  return buyCart;
  }
}

