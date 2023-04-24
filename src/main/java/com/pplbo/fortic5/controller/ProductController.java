package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.pplbo.fortic5.utilities.Mapper.mapToProductResponse;
import static com.pplbo.fortic5.utilities.Mapper.mapToProductResponses;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public String searchProduct(
            @RequestParam("name") String name,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        var productResponses = mapToProductResponses(productService.searchByName(name));
        model.addAttribute("products", productResponses);
        model.addAttribute("user", user);
        return "customer/home";
    }

    @GetMapping("/{id}")
    public String productDetail(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        ProductResponse productResponse = mapToProductResponse(productService.findById(id));
        model.addAttribute("product", productResponse);
        model.addAttribute("user", user);
        model.addAttribute("checkoutRequest", new CheckoutRequest());
        return "product/product";
    }
}