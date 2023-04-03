package com.pplbo.fortic5.controller;

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

import static com.pplbo.fortic5.controller.ProductController.mapToProductResponse;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
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

    @PostMapping("/confirm")
    public String confirmCheckout(
            @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest,
            @AuthenticationPrincipal User user
    ) {
        orderService.save(user, checkoutRequest);
        return "redirect:/";
    }
}
