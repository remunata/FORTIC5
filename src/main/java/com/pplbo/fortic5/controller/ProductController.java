package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }
}