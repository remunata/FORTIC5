package com.pplbo.fortic5.repository;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByProduct(Product product);
}
