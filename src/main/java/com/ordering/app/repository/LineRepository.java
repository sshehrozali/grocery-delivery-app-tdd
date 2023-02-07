package com.ordering.app.repository;

import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
    @Query("SELECT l FROM Line l JOIN l.itemId i WHERE i.itemId LIKE %?1%")
    Line findLineByItemId(String itemId);

    @Modifying
    @Query("DELETE FROM Line l WHERE l.itemId.itemId IN ?1")
    void deleteLinesWithItemIds(List<String> itemIds);
}
