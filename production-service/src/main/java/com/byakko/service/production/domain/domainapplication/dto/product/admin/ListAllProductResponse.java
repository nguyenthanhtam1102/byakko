package com.byakko.service.production.domain.domainapplication.dto.product.admin;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllProductResponse extends ListAllResponse<ProductItemResponse> {

}
