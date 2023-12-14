package com.byakko.service.sales.models.ghn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GHNOrderItem {

    private String name;
    private String code;
    private int quantity;
    private int price;
    private int weight;

}
