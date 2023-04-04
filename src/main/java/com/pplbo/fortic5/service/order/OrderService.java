package com.pplbo.fortic5.service.order;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.user.User;

import java.util.List;

public interface OrderService {

    Order save(User user, CheckoutRequest request);

    Order findById(Integer id);

    List<Order> findOrderByStatus(User user, OrderStatus status);

    List<Order> findOrderHistory(User user, OrderStatus status);

    void updateStatus(Integer id, OrderStatus status);

    boolean deleteById(Integer id);
}
