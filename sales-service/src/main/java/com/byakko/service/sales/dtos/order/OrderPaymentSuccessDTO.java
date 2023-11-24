package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderPaymentSuccessDTO {

    @JsonProperty("vnp_Amount")
    private Long amount;

    @JsonProperty("vnp_BankCode")
    private String bankCode;

    @JsonProperty("vnp_BankTranNo")
    private String bankTranNo;

    @JsonProperty("vnp_CardType")
    private String cartType;

    @JsonProperty("vnp_OrderInfo")
    private String orderInfo;

    @JsonProperty("vnp_PayDate")
    private Long payDate;

    @JsonProperty("vnp_ResponseCode")
    private String responseCode;

    @JsonProperty("vnp_TmnCode")
    private String tmnCode;

    @JsonProperty("vnp_TransactionNo")
    private String transactionNo;

    @JsonProperty("vnp_TransactionStatus")
    private String transactionStatus;

    @JsonProperty("vnp_TxnRef")
    private String txnRef;

    @JsonProperty("vnp_SecureHash")
    private String secureHash;

}
