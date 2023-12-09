package com.byakko.service.sales.service.product.dtos.assets;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateAssetCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String filename;

    private MultipartFile file;

    private String url;

}
