package com.byakko.service.sales.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ListAllProductCommandHandler {

    private final ProductRepository productRepository;

    public ListAllProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ListAllProductResponse handler(ListAllProductCommand command) {
        return productRepository.findAll(command);
    }

}
