package com.byakko.service.production.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.production.domain.domaincore.valueobject.AssetId;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class Asset extends BaseEntity<AssetId> {

    private String filename;
    private String filetype;
    private long size;

    // File có phải là dạng đồ họa không: Image hoặc Video
    private boolean isGraphic;

    // File là dạng đồ họa Image hoặc Video thì sẽ có độ phân giải, kích thước: width, height
    private int width;
    private int height;

    private String contents;

    private String url;

    // Phần path sau domain
    private String slug;

    // Văn bản thay thế nếu file bị lỗi
    private String altText;

    private ZonedDateTime createdAt;

    public void initialize() {
        setId(new AssetId(UUID.randomUUID().toString()));
    }

    public void validate() {
        validateFilename();
        validateUrl();
        validateSlug();
    }

    public void validateFilename() {
        if(filename == null || filename.isBlank()) {
            throw new ValidationException(Map.of("filename", "filename must be not blank"));
        }
    }

    public void validateUrl() {
        if(contents == null && (url == null || url.isBlank()))
            throw new ValidationException(Map.of("url or contents", "url or contents must be not blank"));
    }

    public void validateContents() {
        if(url == null && (contents == null || contents.isBlank())) {
            throw new ValidationException(Map.of("url or contents", "url or contents must be not blank"));
        }
    }

    public void validateSlug() {
        if(slug == null || slug.isBlank())
            throw new ValidationException(Map.of("slug", "slug must be not blank"));
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public boolean isGraphic() {
        return isGraphic;
    }

    public void setGraphic(boolean graphic) {
        isGraphic = graphic;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public static final class Builder {
        private AssetId id;
        private String filename;
        private String filetype;
        private long size;
        private boolean isGraphic;
        private int width;
        private int height;
        private String contents;
        private String url;
        private String slug;
        private String altText;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(AssetId id) {
            this.id = id;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder filetype(String filetype) {
            this.filetype = filetype;
            return this;
        }

        public Builder size(long size) {
            this.size = size;
            return this;
        }

        public Builder isGraphic(boolean isGraphic) {
            this.isGraphic = isGraphic;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder altText(String altText) {
            this.altText = altText;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Asset build() {
            Asset asset = new Asset();
            asset.setId(id);
            asset.setFilename(filename);
            asset.setFiletype(filetype);
            asset.setSize(size);
            asset.setWidth(width);
            asset.setHeight(height);
            asset.setContents(contents);
            asset.setUrl(url);
            asset.setSlug(slug);
            asset.setAltText(altText);
            asset.setCreatedAt(createdAt);
            asset.isGraphic = this.isGraphic;
            return asset;
        }
    }
}
