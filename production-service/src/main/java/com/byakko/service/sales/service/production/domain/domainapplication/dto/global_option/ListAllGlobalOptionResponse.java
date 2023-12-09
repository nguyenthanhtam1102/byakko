package com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllGlobalOptionResponse extends ListAllResponse<OptionResponse> {
}
