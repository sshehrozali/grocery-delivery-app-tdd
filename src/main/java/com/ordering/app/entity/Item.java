package com.ordering.app.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String itemId;
    private String itemName;
    private String description;
    private Float price;
    private Float cost;
}
