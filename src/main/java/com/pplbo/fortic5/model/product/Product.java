package com.pplbo.fortic5.model.product;

import com.pplbo.fortic5.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer rating;

    private Integer stock;

    private String brand;

    private String imageExtension;

    @Enumerated(EnumType.STRING)
    private Kondisi kondisi;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
}