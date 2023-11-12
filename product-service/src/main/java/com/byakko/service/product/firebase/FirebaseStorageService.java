package com.byakko.service.product.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FirebaseStorageService {

    public Blob uploadFile(MultipartFile multipartFile) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();

        return bucket.create(
                multipartFile.getOriginalFilename(),
                multipartFile.getInputStream(),
                multipartFile.getContentType(),
                Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));
    }

    public boolean deleteFile(String blobId) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(blobId);
        if(blob != null) {
            return blob.delete();
        }
        return false;
    }

}
