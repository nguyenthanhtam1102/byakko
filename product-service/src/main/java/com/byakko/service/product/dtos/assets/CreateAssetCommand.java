package com.byakko.service.product.dtos.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class CreateAssetCommand {

    @NotBlank(message = "filename must be not blank")
    private String filename;

    private MultipartFile file;

    private String url;

}
