package com.livraria.sosleitura.security;

import com.livraria.sosleitura.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService serviceCustom;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String jwt;

        if(request.getServletPath().contains("/home")){
            filterChain.doFilter(request,response);
            return;
        }

        if(header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = header.substring(7);
        log.info(jwt);
        String login = jwtService.getSubjectFromToken(jwt);

        if(login != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetailsCustom userDetailsCustom = (UserDetailsCustom) serviceCustom.loadUserByUsername(login);
            if(jwtService.validToken(jwt, userDetailsCustom)){

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetailsCustom,
                        null,
                        userDetailsCustom.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);

    }
}
