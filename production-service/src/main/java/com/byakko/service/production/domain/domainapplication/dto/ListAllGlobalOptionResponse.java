package com.byakko.service.production.domain.domainapplication.dto;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllGlobalOptionResponse extends ListAllResponse<OptionResponse> {
}
