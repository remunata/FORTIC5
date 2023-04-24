package com.pplbo.fortic5.model.order;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    private Integer quantity;

    private String note;

    private Date orderedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
