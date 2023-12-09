package com.byakko.service.sales.service.product.dtos.category;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllCategoriesResponse extends ListAllResponse<CategoryResponse> {
}
