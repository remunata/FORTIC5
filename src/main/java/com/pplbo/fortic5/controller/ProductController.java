package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getBook(@PathVariable Integer id) {
        return findBookById(id);
    }

    private Product findBookById(Integer id) {

        try {
            return productService.findById(id);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    @PostMapping("/")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
