package com.ordering.app.repository;

import com.ordering.app.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query
    Item findItemByItemId(String itemId);

    @Modifying
    @Query("DELETE from Item i WHERE i.itemId in ?1")
    void deleteItemsWithItemIds(List<String> itemId);
}
