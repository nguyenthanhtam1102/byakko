package com.byakko.service.sales.models.ghn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GHNCreateOrderResponse {

    private String code;
    private String code_message_value;
    private String message;
    private String message_display;
    private Data data;

    public static class Data {

        private String order_code;
        private String total_fee;

    }

}
