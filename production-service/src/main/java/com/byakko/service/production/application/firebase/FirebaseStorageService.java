package com.byakko.service.production.application.firebase;

import com.byakko.common.domain.exception.NotFoundException;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.google.firebase.cloud.StorageClient;

@Component
@RequiredArgsConstructor
public class FirebaseStorageService {

    @Value("${firebase.project.id}")
    private static String FIREBASE_PROJECT_ID;

    @Value("${firebase.storage.bucket-name}")
    private String STORAGE_BUCKET_NAME;

    private final FirebaseApp firebaseApp;
    private final Tika tika;

    public Blob uploadImage(String base64Data, String fileName) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        Bucket bucket = StorageClient.getInstance().bucket();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            return bucket.create(
                    fileName,
                    inputStream,
                    tika.detect(inputStream),
                    Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
            );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Blob uploadImage(InputStream inputStream, String fileName, String filetype) {
        Bucket bucket = StorageClient.getInstance().bucket();
        deleteImage(fileName);
        return bucket.create(
                fileName,
                inputStream,
                filetype,
                Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
        );
    }

    public boolean deleteFileByBlobId(String blobId) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Storage storage = bucket.getStorage();
        return storage.delete(blobId);
        // return true if delete success/ false if file not found
    }

    public boolean deleteImage(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(fileName);
        if(blob != null) {
            return blob.delete();
        }
        return false;
    }

}
