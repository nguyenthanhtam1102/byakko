package com.byakko.service.product.dtos.supplier;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllSuppliersResponse extends ListAllResponse<SupplierResponse> {
}
