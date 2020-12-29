package com.hardwaremartapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirbaseIntialize {
	@PostConstruct
    public void initialize() {
        try {
        	
            // InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
        	
            File file = ResourceUtils.getFile("classpath:serviceAccountKey.json");
            InputStream inputStream = new FileInputStream(file);
            
            @SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://hardwaremartapi.firebaseio.com")
                    .setStorageBucket("hardwaremartapi.appspot.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
