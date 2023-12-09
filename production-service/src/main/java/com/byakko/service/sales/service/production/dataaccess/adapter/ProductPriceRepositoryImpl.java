package com.byakko.service.sales.service.production.dataaccess.adapter;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.sales.service.production.dataaccess.entity.ProductPriceEntity;
import com.byakko.service.sales.service.production.dataaccess.mapper.ProductPriceMapper;
import com.byakko.service.sales.service.production.dataaccess.repository.ProductPriceJpaRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductPriceRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    private final ProductPriceJpaRepository productPriceJpaRepository;

    @Override
    public ProductPriceHistoriesResponse getPricesForProduct(GetPriceHistoriesForProductCommand command) {
        Page<ProductPriceEntity> page = productPriceJpaRepository.findByProductOrderByStartDateDesc(
                new ProductEntity(command.getId()),
                PageRequest.of(command.getPage(), command.getLimit())
        );
        return ProductPriceHistoriesResponse.builder()
                .data(page.get().map(ProductPriceMapper::toProductPriceHistoryResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public Optional<ProductPrice> getLastedPriceForProduct(Product product) {
        return productPriceJpaRepository.findByProductAndEndDateIsNull(new ProductEntity(product.getId().getValue()))
                .map(ProductPriceMapper::toProductPriceHistory);
    }

    @Override
    public ProductPrice save(ProductPrice priceHistory) {
        ProductPriceEntity productPriceEntity = productPriceJpaRepository.save(ProductPriceMapper.toProductPriceHistoryEntity(priceHistory));
        return ProductPriceMapper.toProductPriceHistory(productPriceEntity);
    }
}
