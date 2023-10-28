package com.byakko.service.production.application.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.service-account-file}")
    private String SERVICE_ACCOUNT_FILE;

    @Value("${firebase.storage.bucket-name}")
    private String STORAGE_BUCKET_NAME;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource(SERVICE_ACCOUNT_FILE);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .setStorageBucket(STORAGE_BUCKET_NAME)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public Tika getTika() {
        return new Tika();
    }

}
