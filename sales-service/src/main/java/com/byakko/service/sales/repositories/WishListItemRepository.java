package com.byakko.service.sales.repositories;

import com.byakko.service.sales.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, String> {

    List<WishListItem> findAllByCustomerOrderByCreatedAtDesc(String customer);

}
