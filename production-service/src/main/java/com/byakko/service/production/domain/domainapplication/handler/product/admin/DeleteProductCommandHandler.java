package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.DeleteProductCommand;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import com.byakko.service.production.domain.domaincore.valueobject.ProductStatus;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductCommandHandlerHelper productCommandHandlerHelper;

    public DeleteProductCommandHandler(ProductRepository productRepository,
                                       ProductCommandHandlerHelper productCommandHandlerHelper) {
        this.productRepository = productRepository;
        this.productCommandHandlerHelper = productCommandHandlerHelper;
    }

    public void handler(DeleteProductCommand command) {
        Product product = productCommandHandlerHelper.findProductById(new ProductId(command.getId()));

        product.setStatus(ProductStatus.DELETED);

        productRepository.save(product);
    }

}
