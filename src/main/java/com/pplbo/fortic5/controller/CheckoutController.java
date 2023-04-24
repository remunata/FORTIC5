package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.order.OrderStatus;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.order.OrderService;
import com.pplbo.fortic5.service.product.ProductService;
import com.pplbo.fortic5.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.pplbo.fortic5.utilities.Mapper.mapToProductResponse;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping(params = "buy-now")
    public String checkoutProduct(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        ProductResponse product = mapToProductResponse(productService.findById(checkoutRequest.getPID()));
        model.addAttribute("checkoutRequest", checkoutRequest);
        model.addAttribute("product", product);
        model.addAttribute("seller", userService.findById(product.getProduct().getSeller().getId()));
        model.addAttribute("user", user);
        return "checkout/checkout";
    }

    @PostMapping(params = "add-to-cart")
    public String addToCart(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user
    ) {
        orderService.addToCart(checkoutRequest, user);
        return "redirect:/";
    }

    @PostMapping("/confirm")
    public String confirmCheckout(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user
    ) {
        orderService.save(user, checkoutRequest);
        return "redirect:/";
    }

    @PostMapping("/cart")
    public String confirmCartItems(
            @RequestParam("orders") String orders,
            @AuthenticationPrincipal User user
    ) {
        for(var idString : orders.split(",")) {
            var id = Integer.valueOf(idString);
            orderService.updateStatus(id, OrderStatus.WAITING);
        }
        return "redirect:/history";
    }
}
