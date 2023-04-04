package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.order.OrderService;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.pplbo.fortic5.controller.ProductController.mapToProductResponses;
import static com.pplbo.fortic5.controller.SellerController.mapToOrderResponses;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal User user, Model model) {
        var productResponses = mapToProductResponses(productService.findAll());
        model.addAttribute("products", productResponses);
        model.addAttribute("user", user);
        return "customer/home";
    }

    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "customer/cart";
    }

    @GetMapping("/history")
    public String history(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        var ordersWaiting = mapToOrderResponses(orderService.findOrderHistory(user, OrderStatus.WAITING));
        model.addAttribute("ordersWaiting", ordersWaiting);
        var ordersConfirmed = mapToOrderResponses(orderService.findOrderHistory(user, OrderStatus.CONFIRMED));
        model.addAttribute("ordersConfirmed", ordersConfirmed);
        var ordersCanceled = mapToOrderResponses(orderService.findOrderHistory(user, OrderStatus.CANCELED));
        model.addAttribute("ordersCanceled", ordersCanceled);
        return "customer/order_list";
    }

    @PostMapping("/history")
    public String deleteOrder(@RequestParam("id") Integer id) {
        orderService.deleteById(id);
        return "redirect:/history";
    }
}
