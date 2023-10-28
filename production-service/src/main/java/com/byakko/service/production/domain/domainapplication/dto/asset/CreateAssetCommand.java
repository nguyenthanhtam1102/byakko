package com.byakko.service.production.domain.domainapplication.dto.asset;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CreateAssetCommand {

    @NotBlank(message = "filename must be not blank")
    private String filename;

    private String contents;

    private String url;

    @NotBlank(message = "slug must be not blank")
    private String slug;

    @JsonProperty("alt_text")
    private String altText;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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
}
