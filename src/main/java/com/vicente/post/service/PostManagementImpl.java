package com.vicente.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.vicente.post.DTO.PostDTO;

@Service
public class PostManagementImpl implements IPostManagement {

	@Autowired
	private FirebaseInitializer firebaseInitializer;
	@Override
	public List<PostDTO> listPost() {
		List<PostDTO> response = new ArrayList<PostDTO>();
		PostDTO post;
		ApiFuture<QuerySnapshot> querySnap = getCollection().get();
		try {
			for(DocumentSnapshot doc: querySnap.get().getDocuments()) {
				post = doc.toObject(PostDTO.class);
				post.setId(doc.getId());
				response.add(post);
			}
			return response;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Boolean addPost(PostDTO post) {
		Map<String, Object> docData = extractedDocData(post);
		   ApiFuture<WriteResult> writeResult = getCollection().document().create(docData);
		
		try {
			if(writeResult.get()!=null) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			
			return Boolean.FALSE;
		}
		
		
	}



	@Override
	public Boolean updatePost(String id, PostDTO post) {
		Map<String, Object> docData = extractedDocData(post);
		   ApiFuture<WriteResult> writeResult = getCollection().document(id).set(docData);
			try {
				if(writeResult.get()!=null) {
					return Boolean.TRUE;
				}
				return Boolean.FALSE;
			} catch (Exception e) {
				
				return Boolean.FALSE;
			}
			
	}

	@Override
	public Boolean deletePost(String id) {
		   ApiFuture<WriteResult> writeResult = getCollection().document(id).delete();
				try {
					if(writeResult.get()!=null) {
						return Boolean.TRUE;
					}
					return Boolean.FALSE;
				} catch (Exception e) {
					
					return Boolean.FALSE;
				}
				
		}

	
	private CollectionReference getCollection() {
		return firebaseInitializer.getFirestore().collection("post");
	}

	private Map<String, Object> extractedDocData(PostDTO post) {
		Map<String, Object> docData = new HashMap<String, Object>();
		docData.put("title", post.getTitle());
		docData.put("content", post.getContent());
		return docData;
	}

}
