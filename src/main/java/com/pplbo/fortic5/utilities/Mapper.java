package com.pplbo.fortic5.utilities;

import com.pplbo.fortic5.controller.ImageController;
import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.response.OrderResponse;
import com.pplbo.fortic5.model.response.ProductResponse;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

public class Mapper {

    public static OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order, mapToProductResponse(order.getProduct()));
    }

    public static List<OrderResponse> mapToOrderResponses(List<Order> orders) {
        return orders.stream()
                .map(Mapper::mapToOrderResponse)
                .toList();
    }

    public static ProductResponse mapToProductResponse(Product product) {
        String url = product.getId() + product.getImageExtension();
        String imageUrl = MvcUriComponentsBuilder
                .fromMethodName(ImageController.class, "getImage", url)
                .build().toString();
        return new ProductResponse(product, imageUrl);
    }

    public static List<ProductResponse> mapToProductResponses(List<Product> products) {
        return products.stream()
                .map(Mapper::mapToProductResponse)
                .toList();
    }
}
