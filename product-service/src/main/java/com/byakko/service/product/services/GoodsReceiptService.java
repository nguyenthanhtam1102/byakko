package com.byakko.service.product.services;

import com.byakko.service.product.dtos.good_receipt.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface GoodsReceiptService {

    ListAllGoodsReceiptsResponse listAllGoodsReceipts(@Valid ListAllGoodsReceiptCommand command);
    GoodsReceiptResponse getGoodsReceipt(@Valid GetGoodsReceiptCommand command);
    GoodsReceiptResponse createGoodsReceipt(@Valid CreateGoodReceiptCommand command);
    GoodsReceiptResponse updateGoodsReceipt(@Valid UpdateGoodsReceiptCommand command);
    void deleteGoodsReceipt(@Valid DeleteGoodReceiptCommand command);

}
