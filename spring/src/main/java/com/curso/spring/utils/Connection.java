package com.curso.spring.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class Connection {

	
	//https://users-spring-db-default-rtdb.europe-west1.firebasedatabase.app/
	
	//public static Firestore db;

	@PostConstruct
	public void conectarFirebase() {

		try {
			
			FileInputStream serviceAccount =
			  new FileInputStream("usersffirebase.json");
			
			
			FirebaseOptions options = FirebaseOptions.builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setDatabaseUrl("https://users-spring-db-default-rtdb.europe-west1.firebasedatabase.app")
			  .build();
			
			FirebaseApp.initializeApp(options);

			System.out.println("Exito al conectar");

			} catch (IOException e) {
				System.err.println("Error" + e.getMessage());
			}

	}

}
