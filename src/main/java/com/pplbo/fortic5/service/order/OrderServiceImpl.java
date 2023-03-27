package com.pplbo.fortic5.service.order;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.repository.OrderRepository;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public Order save(User user, CheckoutRequest request) {
        Order order = Order.builder()
                .product(productService.findById(request.getPID()))
                .customer(user)
                .quantity(request.getQuantity())
                .note(request.getNote())
                .orderedAt(new Date(System.currentTimeMillis()))
                .status(OrderStatus.WAITING)
                .build();

        return orderRepository.save(order);
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id)
                .orElse(null);
    }
}
