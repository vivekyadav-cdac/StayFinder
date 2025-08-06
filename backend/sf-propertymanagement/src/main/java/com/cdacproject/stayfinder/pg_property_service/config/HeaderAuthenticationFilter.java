package com.cdacproject.stayfinder.pg_property_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Component
@Order(1)
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email = request.getHeader("X-User-Email");
        String role = request.getHeader("X-User-Role");
        String userIdStr = request.getHeader("X-User-Id");
        System.out.println( request.getContentType());

        if (email != null && role != null) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        System.out.println("\n--- Incoming Request ---");
        System.out.println("Method: " + request.getMethod());
        System.out.println("URI: " + request.getRequestURI());

        System.out.println("Headers:");
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            System.out.println("  " + headerName + ": " + request.getHeader(headerName));
        });

        System.out.println("------------------------\n");
        filterChain.doFilter(request, response);
    }
}