package com.byakko.service.sales.service.product.controllers;

import com.byakko.service.product.dtos.good_receipt.*;
import com.byakko.service.sales.service.product.services.GoodsReceiptService;
import com.byakko.service.sales.service.product.dtos.good_receipt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods_receipts")
@RequiredArgsConstructor
public class GoodsReceiptController {

    private final GoodsReceiptService goodsReceiptService;

    @GetMapping
    public ResponseEntity<?> listAllGoodsReceipts(@ModelAttribute ListAllGoodsReceiptCommand command) {
        return ResponseEntity.ok(goodsReceiptService.listAllGoodsReceipts(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGoodsReceipt(@PathVariable("id") String id) {
        return ResponseEntity.ok(goodsReceiptService.getGoodsReceipt(new GetGoodsReceiptCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createGoodsReceipt(@RequestBody CreateGoodReceiptCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(goodsReceiptService.createGoodsReceipt(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGoodReceipt(@PathVariable("id") String id) {
        return ResponseEntity.ok(goodsReceiptService.updateGoodsReceipt(new UpdateGoodsReceiptCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoodReceipt(@PathVariable("id") String id) {
        goodsReceiptService.deleteGoodsReceipt(new DeleteGoodReceiptCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
