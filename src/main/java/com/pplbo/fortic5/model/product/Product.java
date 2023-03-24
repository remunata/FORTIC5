package com.pplbo.fortic5.model.product;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer price;

    private String description;

    private int rating;

    private int stock;

    private String brand;

    @Enumerated(EnumType.STRING)
    private Kondisi kondisi;

    @Enumerated(EnumType.STRING)
    private Category category;
}