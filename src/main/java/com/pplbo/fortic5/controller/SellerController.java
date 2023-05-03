package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.request.ProductRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.image.ImageStorageService;
import com.pplbo.fortic5.service.order.OrderService;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pplbo.fortic5.utilities.Mapper.*;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;
    private final OrderService orderService;
    private final ImageStorageService imageService;

    @GetMapping
    public String dashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        List<ProductResponse> productResponses = mapToProductResponses(productService.findBySeller(user));
        model.addAttribute("products", productResponses);
        return "seller/dashboard";
    }

    @PostMapping("/product")
    public String searchProduct(
            @RequestParam("name") String name,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        var productResponses = mapToProductResponses(productService.findBySeller(user).stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList());
        model.addAttribute("products", productResponses);
        model.addAttribute("user", user);
        return "seller/dashboard";
    }

    @GetMapping("/order")
    public String orderList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);

        var ordersWaiting = mapToOrderResponses(orderService.findOrderByStatus(user, OrderStatus.WAITING));
        model.addAttribute("ordersWaiting", ordersWaiting);

        var ordersConfirmed = mapToOrderResponses(orderService.findOrderByStatus(user, OrderStatus.CONFIRMED));
        model.addAttribute("ordersConfirmed", ordersConfirmed);

        return "seller/order_list";
    }

    @PostMapping(value = "/order")
    public String confirmOrder(@RequestParam("id") Integer id) {
        orderService.confirmOrder(id);
        return "redirect:/dashboard/order";
    }

    @GetMapping("/add")
    public String addProductForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("request", new ProductRequest());
        return "seller/add_product";
    }

    @PostMapping("/add")
    public String addProduct(@AuthenticationPrincipal User user, @ModelAttribute("request") ProductRequest request) {
        var product = productService.save(request, user);
        imageService.save(request.getImage(), product.getId(), product.getImageExtension());
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id,
            Model model
    ) {
        var product = productService.findById(id);
        var request = ProductRequest.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .brand(product.getBrand())
                .stock(product.getStock())
                .kondisi(product.getKondisi())
                .build();
        model.addAttribute("request", request);
        model.addAttribute("user", user);
        return "seller/edit_product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, @ModelAttribute("request") ProductRequest request) {
        var product = productService.edit(request, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/product/{id}")
    public String productPreview(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        ProductResponse productResponse = mapToProductResponse(productService.findById(id));
        model.addAttribute("product", productResponse);
        model.addAttribute("user", user);
        model.addAttribute("checkoutRequest", new CheckoutRequest());
        return "seller/product_preview";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        var del = productService.deleteById(id);
        return "redirect:/dashboard";
    }
}
