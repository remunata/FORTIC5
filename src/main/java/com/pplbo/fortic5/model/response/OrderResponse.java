package com.pplbo.fortic5.model.response;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Order order;

    private ProductResponse product;
}
