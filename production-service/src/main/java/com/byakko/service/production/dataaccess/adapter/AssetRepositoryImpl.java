package com.byakko.service.production.dataaccess.adapter;

import com.byakko.service.production.application.firebase.FirebaseStorageService;
import com.byakko.service.production.dataaccess.entity.AssetEntity;
import com.byakko.service.production.dataaccess.mapper.AssetMapper;
import com.byakko.service.production.dataaccess.repository.AssetJpaRepository;
import com.byakko.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.production.domain.domaincore.entity.Asset;
import com.google.cloud.storage.Blob;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {

    private final AssetJpaRepository assetJpaRepository;
    private final FirebaseStorageService firebaseStorageService;
    private final Tika tika;

    @Transactional
    @Override
    public Asset save(Asset asset) {
        Blob blob = null;

        try {
            AssetEntity assetEntity = AssetEntity.builder()
                    .id(asset.getId().getValue())
                    .filename(asset.getFilename())
                    .url(asset.getUrl())
                    .slug(asset.getSlug())
                    .altText(asset.getAltText())
                    .build();

            if(asset.getContents() != null) {
                byte[] imageBytes = Base64.getDecoder().decode(asset.getContents());
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                String filetype = tika.detect(inputStream);

                blob = firebaseStorageService.uploadImage(inputStream, asset.getId().getValue(), filetype);
                assetEntity.setFiletype(blob.getContentType());
                assetEntity.setUrl(blob.getMediaLink());
                assetEntity.setSize(blob.getSize());
            }

            assetEntity = assetJpaRepository.save(assetEntity);

            return AssetMapper.toAsset(assetEntity);
        } catch (Exception ex) {
            ex.printStackTrace();

            if(blob != null) {
                firebaseStorageService.deleteFileByBlobId(blob.getBucket());
            }

            throw new RuntimeException("Không thể lưu asset");
        }
    }

    @Override
    public Page<Asset> findAll(int page, int limit, String query) {
        return assetJpaRepository.findAll(PageRequest.of(page, limit)).map(AssetMapper::toAsset);
    }

    @Override
    public Optional<Asset> findById(String id) {
        return assetJpaRepository.findById(id).map(AssetMapper::toAsset);
    }

    @Transactional
    @Override
    public void delete(Asset asset) {
        if(!firebaseStorageService.deleteImage(asset.getId().getValue()))
            throw new RuntimeException("Delete file không thành công");
        assetJpaRepository.deleteById(asset.getId().getValue());
    }
}
