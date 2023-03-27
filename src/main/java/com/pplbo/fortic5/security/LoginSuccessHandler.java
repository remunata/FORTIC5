package com.pplbo.fortic5.security;

import com.pplbo.fortic5.model.user.Role;
import com.pplbo.fortic5.model.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws ServletException, IOException {

        User user = (User) authentication.getPrincipal();
        String redirectURL = request.getContextPath();

        if(user.hasRole(Role.CUSTOMER))
            redirectURL = "/";
        else if(user.hasRole(Role.SELLER))
            redirectURL = "/dashboard";

        response.sendRedirect(redirectURL);
    }
}
