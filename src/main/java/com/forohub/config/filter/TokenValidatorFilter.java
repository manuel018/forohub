package com.forohub.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


public class TokenValidatorFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;


    public TokenValidatorFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //El token vienen en el header y se obtiene
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        //se valida si se envia el token o
        if (jwtToken != null) {
            jwtToken = jwtToken.substring(7);//apartir del 7 despues del "bearer "
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
            String username = jwtUtils.extractUser(decodedJWT);

            //Setear en el Security Context Holder
            SecurityContext context = SecurityContextHolder.getContext(); //obtenemos el contexto del spring security
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(authority));

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }

        //continua con los filtros siguientes
        filterChain.doFilter(request, response);
    }
}
