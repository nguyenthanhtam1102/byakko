package com.byakko.service.sales.dtos.order;

import com.byakko.common.application.dto.ListAllCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ListAllOrderCommand extends ListAllCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

}
