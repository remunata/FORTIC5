package com.pplbo.fortic5.controller;

import com.pplbo.fortic5.model.request.RegisterRequest;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

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
