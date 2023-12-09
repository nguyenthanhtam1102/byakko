package com.byakko.service.sales.service.product.dtos.global_option;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllOptionsResponse extends ListAllResponse<OptionResponse> {
}
