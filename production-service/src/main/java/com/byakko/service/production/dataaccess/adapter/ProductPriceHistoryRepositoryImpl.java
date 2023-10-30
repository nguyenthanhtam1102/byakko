package com.byakko.service.production.dataaccess.adapter;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceHistoryEntity;
import com.byakko.service.production.dataaccess.mapper.ProductPriceHistoryMapper;
import com.byakko.service.production.dataaccess.repository.ProductPriceHistoryJpaRepository;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductPriceHistoryRepository;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPriceHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPriceHistoryRepositoryImpl implements ProductPriceHistoryRepository {

    private final ProductPriceHistoryJpaRepository productPriceHistoryJpaRepository;

    @Override
    public ProductPriceHistoriesResponse getPriceHistoriesForProduct(GetPriceHistoriesForProductCommand command) {
        Page<ProductPriceHistoryEntity> page = productPriceHistoryJpaRepository.findByProductOrderByStartDateDesc(
                new ProductEntity(command.getId()),
                PageRequest.of(command.getPage(), command.getLimit())
        );
        return ProductPriceHistoriesResponse.builder()
                .data(page.get().map(ProductPriceHistoryMapper::toProductPriceHistoryResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public Optional<ProductPriceHistory> getLastedPriceForProduct(Product product) {
        return productPriceHistoryJpaRepository.findByProductAndEndDateIsNull(new ProductEntity(product.getId().getValue()))
                .map(ProductPriceHistoryMapper::toProductPriceHistory);
    }

    @Override
    public ProductPriceHistory save(ProductPriceHistory priceHistory) {
        ProductPriceHistoryEntity productPriceHistoryEntity = productPriceHistoryJpaRepository.save(ProductPriceHistoryMapper.toProductPriceHistoryEntity(priceHistory));
        return ProductPriceHistoryMapper.toProductPriceHistory(productPriceHistoryEntity);
    }
}
