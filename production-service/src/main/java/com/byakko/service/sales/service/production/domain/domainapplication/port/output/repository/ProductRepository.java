package com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductRepository {

    ListAllProductResponse findAll(ListAllProductCommand command);
    ProductFilterResponse filter(ProductFilterCommand command);
    Optional<Product> findById(ProductId id);
    Product save(Product product);

}
