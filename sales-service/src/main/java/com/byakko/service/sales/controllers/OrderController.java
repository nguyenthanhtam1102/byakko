package com.byakko.service.sales.controllers;

import com.byakko.common.application.dto.ListAllCommand;
import com.byakko.service.sales.dtos.order.*;
import com.byakko.service.sales.services.OrderService;
import com.byakko.service.sales.vnpay.VNPayConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listAllOrders(@PathVariable("id") String id,@ModelAttribute ListAllCommand command) {
        ListAllOrderCommand list = new ListAllOrderCommand();
        list.setCustomerId(id);
        list.setPage(command.getPage());
        list.setLimit(command.getLimit());
        list.setQuery(command.getQuery());
        list.setSortBy(command.getSortBy());
        list.setSortDirection(command.getSortDirection());
        return ResponseEntity.ok(orderService.listAllOrders(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        return ResponseEntity.ok(orderService.getOrder(new GetOrderCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(command));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") String id) {
        orderService.cancelOrder(new CancelOrderCommand(id));
        return ResponseEntity.ok("Cancel order success");
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<?> paymentOrder(@PathVariable("id") String id, HttpServletRequest request) {
        String paymentUrl = orderService.paymentOrder(new PaymentOrderCommand(id, VNPayConfigs.getIpAddress(request)));
        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping("/payment-success")
    public ResponseEntity<?> paymentSuccess(@RequestParam("vnp_TxnRef") String vnpTxnRef) {
        orderService.orderPaymentSuccess(vnpTxnRef);
        return ResponseEntity.ok("Payment success");
    }

}
