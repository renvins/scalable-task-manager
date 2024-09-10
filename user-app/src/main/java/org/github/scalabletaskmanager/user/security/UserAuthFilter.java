package org.github.scalabletaskmanager.user.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.github.scalabletaskmanager.user.service.UserService;

import java.io.IOException;
import java.util.Base64;

public class UserAuthFilter implements Filter {

    private final UserService userService;

    public UserAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String encodedToken = request.getHeader("Authorization");
        String basicToken = encodedToken.replace("Basic ", "");

        String decodedToken = new String(Base64.getDecoder().decode(basicToken));
        var userAndPassword = decodedToken.split(":");

        boolean credentialsOk = userService.login(userAndPassword[0], userAndPassword[1]);

        if (!credentialsOk) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
