package com.byakko.service.sales.service.product.dtos.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AssetMinResponse {

    private String id;
    private String filename;
    private String url;

}
