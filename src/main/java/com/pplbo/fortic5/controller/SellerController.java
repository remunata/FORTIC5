package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.request.ProductRequest;
import com.pplbo.fortic5.model.response.OrderResponse;
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

import static com.pplbo.fortic5.controller.ProductController.mapToProductResponse;
import static com.pplbo.fortic5.controller.ProductController.mapToProductResponses;

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

    @GetMapping("/order")
    public String orderList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);

        var ordersWaiting = mapToOrderResponses(orderService.findOrderByStatus(user, OrderStatus.WAITING));
        model.addAttribute("ordersWaiting", ordersWaiting);

        var ordersConfirmed = mapToOrderResponses(orderService.findOrderByStatus(user, OrderStatus.CONFIRMED));
        model.addAttribute("ordersConfirmed", ordersConfirmed);

        var ordersCanceled = mapToOrderResponses(orderService.findOrderByStatus(user, OrderStatus.CANCELED));
        model.addAttribute("ordersCanceled", ordersCanceled);

        return "seller/order_list";
    }

    @PostMapping(value = "/order", params = "confirm")
    public String confirmOrder(@RequestParam("id") Integer id) {
        orderService.updateStatus(id, OrderStatus.CONFIRMED);
        return "redirect:/dashboard/order";
    }

    @PostMapping(value = "/order", params = "cancel")
    public String cancelOrder(@RequestParam("id") Integer id) {
        orderService.updateStatus(id, OrderStatus.CANCELED);
        return "redirect:/dashboard/order";
    }

    @GetMapping("/add")
    public String addProductForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("request", new ProductRequest());
        return "seller/add_product";
    }

    @PostMapping("/add")
    public String addProduct(
            @AuthenticationPrincipal User user,
            @ModelAttribute("request") ProductRequest request,
            Model model
    ) {
        var product = productService.save(request, user);
        imageService.save(request.getImage(), product.getId(), product.getImageExtension());

        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
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

    static List<OrderResponse> mapToOrderResponses(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderResponse(order, mapToProductResponse(order.getProduct())))
                .toList();
    }
}
