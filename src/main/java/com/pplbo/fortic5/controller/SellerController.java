package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.response.OrderResponse;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.order.OrderService;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String orderList(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        List<OrderResponse> orderResponses = orderService.findOrderWaiting(user).stream()
                        .map(order -> OrderResponse.builder()
                                .order(order)
                                .product(new ProductResponse(order.getProduct()))
                                .build()
                        ).toList();
        model.addAttribute("ordersWaiting", orderResponses);
        return "seller/list";
    }
}
