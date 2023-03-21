package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import com.pplbo.fortic5.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String homePage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
