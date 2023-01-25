package com.ordering.app.entity;

import com.ordering.app.entity.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Line {
    @Id
    private Integer id;
    @OneToOne
    private Item itemId;
    private Integer quantity;
}
