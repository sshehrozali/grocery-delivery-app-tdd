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
public class Offer {
    @Id
    private Integer id;
    private String offerId;
    private String offerName;
    private String description;
    @OneToOne
    private Item itemId;
    private Float priceReduction;
    private Integer quantityThreshold;
}
