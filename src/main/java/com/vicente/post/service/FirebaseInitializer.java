package com.vicente.post.service;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {

	@PostConstruct
	private void initFirestore() throws Exception {

		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firestore-key.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://post-management-ae94d.firebaseio.com/")
				.build();

		if(FirebaseApp.getApps().isEmpty()) {
		FirebaseApp.initializeApp(options);
		}
	}
	public Firestore getFirestore() {
		return FirestoreClient.getFirestore();
	}
}
