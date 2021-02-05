package com.hardwaremartapi.service;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.google.firebase.cloud.FirestoreClient;
import com.hardwaremartapi.bean.Comment;
import com.hardwaremartapi.bean.Order;

@Service
public class CommentService {

	CollectionReference ref;

	public Comment deleteComment(String productId, String commentId) throws Exception, Exception {
		Firestore firestore = FirestoreClient.getFirestore();
		Comment comment = firestore.collection("Product").document(productId).collection("Comment").document(commentId)
				.get().get().toObject(Comment.class);
		if (comment != null)
			firestore.collection("Product").document(productId).collection("Comment").document(commentId).delete();
		return comment;
	}

	public Comment commentProduct(Comment comment) {
		Firestore firestore = FirestoreClient.getFirestore();
		String commentId = firestore.collection("Product").document(comment.getProductId()).collection("Comment")
				.document().getId().toString();
		comment.setTimestamp(System.currentTimeMillis());
		comment.setCommentId(commentId);
		firestore.collection("Product").document(comment.getProductId()).collection("Comment").document(commentId)
				.set(comment);
		return comment;
	}

	public ArrayList<Comment> getCommentOfProduct(String productId) throws Exception, Exception {
		Firestore firestore = FirestoreClient.getFirestore();
		ArrayList<Comment> commentList = new ArrayList<>();
		ApiFuture<QuerySnapshot> apiFuture = firestore.collection("Product").document(productId).collection("Comment")
				.orderBy("timestamp", Direction.DESCENDING).limit(10).get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshot = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshot) {
			Comment comment = document.toObject(Comment.class);
			String product = comment.getProductId();
			if (product.equals(productId))
				commentList.add(comment);
		}
		return commentList;
	}

	public Comment updateComment(Comment comment, String commentId) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		Comment com = firestore.collection("Product").document(comment.getProductId()).collection("Comment")
				.document(commentId).get().get().toObject(Comment.class);
		comment.setTimestamp(System.currentTimeMillis());
		comment.setUserImg(com.getUserImg());
		comment.setUserName(com.getUserName());
		comment.setUserId(com.getUserId());
		comment.setCommentId(commentId);
		firestore.collection("Product").document(comment.getProductId()).collection("Comment").document(commentId)
				.set(comment);
		return comment;
	}
}
