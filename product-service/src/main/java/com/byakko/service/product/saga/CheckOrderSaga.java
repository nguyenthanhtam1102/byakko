package com.byakko.service.product.saga;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.infrastructure.kafka.constants.KafkaTopic;
import com.byakko.infrastructure.kafka.producer.KafkaProducer;
import com.byakko.service.product.events.Order;
import com.byakko.service.product.events.OrderCheckedEvent;
import com.byakko.service.product.events.OrderCreatedEvent;
import com.byakko.service.product.events.OrderDetail;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductPrice;
import com.byakko.service.product.models.ProductVariant;
import com.byakko.service.product.repositories.ProductPriceRepository;
import com.byakko.service.product.repositories.ProductRepository;
import com.byakko.service.product.repositories.ProductVariantRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckOrderSaga {

    @Value("${kafka.retry}")
    private int KAFKA_RETRY;

    @Value("${request.timeout}")
    private int REQUEST_TIMEOUT;

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductPriceRepository productPriceRepository;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    public void checkOrder(OrderCreatedEvent event) {
        System.out.println("Xử lí checkOrder");
        Order order = event.getOrder();

        Set<OrderDetail> orderDetails = order.getOrderDetails().stream().map(orderDetail -> {
            Product product = productRepository.findById(orderDetail.getProduct()).
                    orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", orderDetail.getProduct())));
            try {
                orderDetail.setProduct(objectMapper.writeValueAsString(product));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            if(orderDetail.getVariant() != null) {
                ProductVariant productVariant = productVariantRepository.findByIdAndProductId(product.getId(), orderDetail.getVariant())
                        .orElseThrow(() -> new NotFoundException(String.format("Product variant with id %s not found", orderDetail.getVariant())));
                try {
                    orderDetail.setVariant(objectMapper.writeValueAsString(productVariant));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            ProductPrice productPrice = productPriceRepository.findCurrentPriceByProduct(product, LocalDate.now()).stream().findFirst()
                    .orElseThrow(() -> new NotFoundException(String.format("Product price for product id %s not found", product.getId())));
            orderDetail.setUnitPrice(productPrice.getPrice());

            return orderDetail;
        }).collect(Collectors.toSet());

        order.setOrderDetails(orderDetails);

        sendOrderCheckedEvent(event.getId(), new OrderCheckedEvent(event.getId(), order, ZonedDateTime.now().toEpochSecond()));
    }

    private void sendOrderCheckedEvent(String eventId, OrderCheckedEvent event) {
        System.out.println("Send order checked event to sales-service");

        String orderCheckedEventJson;
        try {
            orderCheckedEventJson = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException ex) {
            System.out.println("Khong the chuyen orderCheckedEvent sang json");
            return;
        }

        kafkaProducer.send(KafkaTopic.ORDER_CHECKED_TOPIC, orderCheckedEventJson)
                .retry(KAFKA_RETRY)
                .doOnError(throwable -> {
                    System.out.println("Gui message den kafka khong thanh cong: " + throwable.getMessage());
                })
                .subscribe();
    }

}
