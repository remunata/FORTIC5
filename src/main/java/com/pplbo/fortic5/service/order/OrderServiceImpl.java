package com.pplbo.fortic5.service.order;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.repository.OrderRepository;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Order> findOrderByStatus(User user, OrderStatus status) {
        List<Order> orders = new ArrayList<>(user.getProducts().size());

        for (Product product : productService.findBySeller(user)) {
            orderRepository.findByProduct(product)
                    .stream()
                    .filter(order -> order.getStatus().equals(status))
                    .forEach(orders::add);
        }

        return orders;
    }

    @Override
    public List<Order> findOrderHistory(User user, OrderStatus status) {
        return orderRepository.findByCustomer(user).stream()
                .filter(order -> order.getStatus().equals(status))
                .toList();
    }

    @Override
    public void updateStatus(Integer id, OrderStatus status) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public boolean deleteById(Integer id) {
        orderRepository.deleteById(id);
        return true;
    }
}
