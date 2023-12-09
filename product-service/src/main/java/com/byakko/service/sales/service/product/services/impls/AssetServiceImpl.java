package com.byakko.service.sales.service.product.services.impls;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.assets.*;
import com.byakko.service.sales.service.product.firebase.FirebaseStorageService;
import com.byakko.service.sales.service.product.mappers.AssetMapper;
import com.byakko.service.sales.service.product.models.Asset;
import com.byakko.service.sales.service.product.repositories.AssetRepository;
import com.byakko.service.sales.service.product.services.AssetService;
import com.byakko.service.sales.service.product.dtos.assets.*;
import com.google.cloud.storage.Blob;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final FirebaseStorageService firebaseStorageService;

    @Override
    public ListAllAssetResponse listAllAssets(ListAllAssetCommand command) {
        Sort.Direction direction;
        if(command.getSortDirection() == null || command.getSortDirection().isBlank()) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else {
            throw new RuntimeException("Sort direction not correct");
        }

        Sort sort;
        if(command.getSortBy() == null || command.getSortBy().isBlank()) {
            sort = Sort.by(direction, "createdAt");
        } else if(command.getSortBy().equalsIgnoreCase("FILENAME")) {
            sort = Sort.by(direction, "filename");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<Asset> page = assetRepository.findAllByIdOrName(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllAssetResponse.builder()
                .data(page.stream().map(AssetMapper::toAssetResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public AssetResponse getAsset(GetAssetCommand command) {
        Asset asset = assetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", command.getId())));
        return AssetMapper.toAssetResponse(asset);
    }

    @Transactional
    @Override
    public AssetResponse createAsset(CreateAssetCommand command) {
        Asset asset = new Asset();
        asset.setFilename(command.getFilename());
        asset.setUrl(command.getUrl());

        if(command.getFile() != null) {
            String contentType = command.getFile().getContentType();
            System.out.println(contentType);

            if(contentType != null && !contentType.isBlank()
                    && (contentType.startsWith("image/") || contentType.startsWith("video/"))) {
                try {
                    Blob blob = firebaseStorageService.uploadFile(command.getFile());
                    asset.setBlobId(command.getFile().getOriginalFilename());

                    asset.setUrl(blob.getMediaLink());
                    asset.setSize(command.getFile().getSize());
                    asset.setContentType(command.getFile().getContentType());
                } catch (IOException ex) {
                    throw new RuntimeException("Upload file to firebase error");
                }
            } else {
                throw new ValidationException(Map.of("file", "content type is null or not image/video"));
            }
        }

        assetRepository.save(asset);

        return AssetMapper.toAssetResponse(asset);
    }

    @Transactional
    @Override
    public AssetResponse updateAsset(UpdateAssetCommand command) {
        Asset asset = assetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", command.getId())));

        if(command.getFilename() != null) {
            if(command.getFilename().isBlank())
                throw new ValidationException(Map.of("filename", "filename must be not blank"));
            asset.setFilename(command.getFilename());
        }

        if(command.getUrl() != null) {
            if(command.getUrl().isBlank())
                throw new ValidationException(Map.of("url", "url must be not blank"));
            asset.setBlobId(null);
            asset.setUrl(command.getUrl());
            asset.setSize(null);
            asset.setContentType(null);
        }

        if(command.getFile() != null) {
            String contentType = command.getFile().getContentType();
            if(contentType != null && !contentType.isBlank()
                    && (contentType.startsWith("image/") || contentType.startsWith("video/"))) {
                try {
                    Blob blob = firebaseStorageService.uploadFile(command.getFile());
                    asset.setBlobId(command.getFile().getOriginalFilename());
                    asset.setUrl(blob.getMediaLink());
                    asset.setSize(command.getFile().getSize());
                    asset.setContentType(command.getFile().getContentType());
                } catch (IOException ex) {
                    throw new RuntimeException("Upload file to firebase error");
                }
            } else {
                throw new ValidationException(Map.of("file", "content type is null or not image/video"));
            }
        }

        asset.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        assetRepository.save(asset);

        return AssetMapper.toAssetResponse(asset);
    }

    @Transactional
    @Override
    public void deleteAsset(DeleteAssetCommand command) {
        Asset asset = assetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", command.getId())));

        if(asset.getBlobId() != null) {
            if(!firebaseStorageService.deleteFile(asset.getBlobId()))
                throw new RuntimeException("Xóa file trên firebase không thành công");
        }

        assetRepository.deleteAssetFromProduct(asset.getId());
        assetRepository.delete(asset);
    }

}
