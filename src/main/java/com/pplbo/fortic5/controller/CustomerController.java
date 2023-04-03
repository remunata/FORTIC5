package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.pplbo.fortic5.controller.ProductController.mapToProductResponses;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final ProductService productService;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        List<ProductResponse> productResponses = mapToProductResponses(productService.findAll());
        model.addAttribute("products", productResponses);
        return "customer/home";
    }

    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "customer/cart";
    }
}
