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

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/")
    public String homePage(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        List<ProductResponse> productResponses = productService.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
        model.addAttribute("products", productResponses);
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        List<ProductResponse> productResponses = user.getProducts().stream()
                .map(ProductResponse::new)
                .toList();
        model.addAttribute("products", productResponses);
        return "seller/dashboard";
    }
}
