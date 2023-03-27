package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.order.OrderService;
import com.pplbo.fortic5.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping
    public String checkoutProduct(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        ProductResponse product = new ProductResponse(productService.findById(checkoutRequest.getPID()));
        model.addAttribute("checkoutRequest", checkoutRequest);
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "checkout";
    }

    @PostMapping("/confirm")
    public String confirmCheckout(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        orderService.save(user, checkoutRequest);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
