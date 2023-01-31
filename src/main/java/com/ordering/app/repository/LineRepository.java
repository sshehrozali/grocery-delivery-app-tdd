package com.ordering.app.repository;

import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
    @Query
    Line findLineByItemId(Item item);
}
