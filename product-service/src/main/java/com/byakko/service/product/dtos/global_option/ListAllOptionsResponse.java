package com.byakko.service.product.dtos.global_option;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllOptionsResponse extends ListAllResponse<OptionResponse> {
}
