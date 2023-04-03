package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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

    static ProductResponse mapToProductResponse(Product product) {
        String url = product.getId() + product.getImageExtension();
        String imageUrl = MvcUriComponentsBuilder
                .fromMethodName(ImageController.class, "getImage", url)
                .build().toString();
        return new ProductResponse(product, imageUrl);
    }

    static List<ProductResponse> mapToProductResponses(List<Product> products) {
        return products.stream()
                .map(ProductController::mapToProductResponse)
                .toList();
    }
}