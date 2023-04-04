package com.pplbo.fortic5.repository;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByProduct(Product product);

    List<Order> findByCustomer(User user);
}
