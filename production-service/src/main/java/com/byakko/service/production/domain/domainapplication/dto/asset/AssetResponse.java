package com.byakko.service.production.domain.domainapplication.dto.asset;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetResponse {

    private String id;
    private String filename;
    private String filetype;
    private long size;
    private String url;
    private String slug;

    @JsonProperty("is_graphic")
    private boolean isGraphic;
    private Graphic graphic;

    @JsonProperty("alt_text")
    private String altText;

    @JsonProperty("created_at")
    private long createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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

    public boolean isGraphic() {
        return isGraphic;
    }

    public void setGraphic(boolean graphic) {
        isGraphic = graphic;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public static class Graphic {
        private int width;
        private int height;

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

        public Graphic(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static final class Builder {
        private String id;
        private String filename;
        private String filetype;
        private long size;
        private String url;
        private String slug;
        private boolean isGraphic;
        private Graphic graphic;
        private String altText;
        private long createdAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
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

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder isGraphic(boolean isGraphic) {
            this.isGraphic = isGraphic;
            return this;
        }

        public Builder graphic(Graphic graphic) {
            this.graphic = graphic;
            return this;
        }

        public Builder altText(String altText) {
            this.altText = altText;
            return this;
        }

        public Builder createdAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AssetResponse build() {
            AssetResponse assetResponse = new AssetResponse();
            assetResponse.setId(id);
            assetResponse.setFilename(filename);
            assetResponse.setFiletype(filetype);
            assetResponse.setSize(size);
            assetResponse.setUrl(url);
            assetResponse.setSlug(slug);
            assetResponse.setGraphic(graphic);
            assetResponse.setAltText(altText);
            assetResponse.setCreatedAt(createdAt);
            assetResponse.isGraphic = this.isGraphic;
            return assetResponse;
        }
    }
}
