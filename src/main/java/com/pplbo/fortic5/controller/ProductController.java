package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public String productDetail(
            @PathVariable Integer id,
            Model model
    ) {
        ProductResponse productResponse = new ProductResponse(productService.findById(id));
        model.addAttribute("product", productResponse);
        model.addAttribute("checkoutRequest", new CheckoutRequest());
        return "product";
    }

    @GetMapping("/add")
    public String addProductForm() {
        return "add";
    }
}