package com.byakko.service.sales.models.ghn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GHNOrder {

    private int payment_type_id = 2;
    private String required_note = "KHONGCHOXEMHANG";
    private String from_name = "Byakko";
    private String from_phone = "0987654321";
    private String from_address = "72 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Vietnam";
    private String from_ward_name = "Phường 14";
    private String from_district_name = "Quận 10";
    private String from_province_name = "HCM";
    private String client_order_code;
    private String to_name;
    private String to_phone;
    private String to_address;
    private String to_ward_code;
    private int to_district_id;
    private int cod_amount;
    private int weight = 200;
    private int insurance_value = 10000000;
    private int service_type_id = 5;
    private List<GHNOrderItem> items;

}
