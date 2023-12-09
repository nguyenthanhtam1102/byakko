package com.byakko.service.sales.service.production.domain.domainapplication.handler.product.customer;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductFilterCommandHandler {

    private final ProductRepository productRepository;

    public ProductFilterCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductFilterResponse handler(ProductFilterCommand command) {
        return productRepository.filter(command);
    }

}
