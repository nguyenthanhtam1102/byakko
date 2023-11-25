package com.byakko.service.sales.dtos.wish_list_item;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class WishListResponse extends ListAllResponse<WishListItemResponse> {
}
