package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.request.RegisterRequest;
import com.pplbo.fortic5.model.response.ProductResponse;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import com.pplbo.fortic5.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String homePage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<ProductResponse> productResponses = productService.findAll()
                        .stream()
                        .map(ProductResponse::new)
                        .toList();
        model.addAttribute("productResponses", productResponses);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registration(
            @ModelAttribute("user") RegisterRequest request,
            Model model
    ) {

        try {
            User existingUser = userService.findByUsername(request.getUsername());
            model.addAttribute("error", "username telah digunakan");
            return "register";
        } catch (UsernameNotFoundException exception) {
            userService.register(request);
            return "redirect:/";
        }
    }
}
